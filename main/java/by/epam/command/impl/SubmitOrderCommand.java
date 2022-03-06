package by.epam.command.impl;

import by.epam.command.CommandResult;
import by.epam.command.CommandResultType;
import by.epam.command.ICommand;
import by.epam.dao.IOrderDao;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.IFlightDao;
import by.epam.dao.impl.OrderDao;
import by.epam.entity.Flight;
import by.epam.entity.Order;
import by.epam.entity.OrderInfo;
import by.epam.entity.User;
import by.epam.exceptions.DaoException;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubmitOrderCommand implements ICommand {
    private static final Logger logger = LogManager.getLogger(SubmitOrderCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            IFlightDao flightDao = new FlightDao();
            IOrderDao orderDao = new OrderDao();
            User user = (User) session.getAttribute(ConstantsJSP.USER);
            Flight flight = (Flight) session.getAttribute(ConstantsJSP.ORDER);
            int amountForOrder = Integer.parseInt(req.getParameter(ConstantsJSP.AMOUNT));
            if (amountForOrder <= 0) {
                req.setAttribute(ConstantsJSP.ERROR_NAME, "Invalid amount of tickets, try again!");
                return new CommandResult(ConstantsJSP.RESERVE_PAGE_URL, CommandResultType.FORWARD);
            }
            String baggage = req.getParameter(ConstantsJSP.BAGGAGE);
            int totalCost = flight.getPrice() * amountForOrder;
            if ("on".equals(baggage)) {
                totalCost += 15;
            }
            Order order = new Order(user.getId(), flight.getId(), amountForOrder, totalCost);
            orderDao.create(order);
            flight.setAmount(flight.getAmount() - amountForOrder);
            flightDao.update(flight);
            List<Order> orders = orderDao.findAll(20, 0);
            List<OrderInfo> infos = new ArrayList<>();
            for (Order ord : orders) {
                Optional<Flight> optionalFlight = flightDao.findById(ord.getFlightId());
                optionalFlight.ifPresent(value -> infos.add(new OrderInfo(ord, value)));
            }
            session.setAttribute(ConstantsJSP.ORDERS, infos);
            return new CommandResult(ConstantsJSP.PROFILE_PAGE_URL, CommandResultType.FORWARD);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            return new CommandResult(ConstantsJSP.ERROR_PAGE_URL, CommandResultType.FORWARD);
        }
    }
}


