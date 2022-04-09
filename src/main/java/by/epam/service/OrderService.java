package by.epam.service;

import by.epam.dao.IFlightDao;
import by.epam.dao.IOrderDao;
import by.epam.dao.impl.FlightDao;
import by.epam.dao.impl.OrderDao;
import by.epam.entity.*;
import by.epam.exceptions.DaoException;
import by.epam.exceptions.ServiceException;
import by.epam.utils.ConstantsJSP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class.getName());

    public static void createOrder(User user, Flight flight, int amountForOrder) throws ServiceException {
        try {
            IOrderDao orderDao = new OrderDao();
            int totalCost = flight.getPrice() * amountForOrder;
            Order order = new Order(user.getId(), flight.getId(), amountForOrder, totalCost);
            orderDao.create(order);
        } catch (DaoException e) {
            logger.error("Unable to create order!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void setOrderInfoByAll(HttpSession session) throws ServiceException { //!!!!!!!!!!!!!
        try {
            IOrderDao orderDao = new OrderDao();
            List<Order> orders = orderDao.findAll(ConstantsJSP.LIMIT, ConstantsJSP.OFFSET);
            createOrderInfo(session, orders);
        } catch (DaoException e) {
            logger.error("Unable to set orderInfo!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static void setOrderInfoByUserId(HttpSession session, User user) throws ServiceException { //!!!!!!!!!!!!!
        try {
            IOrderDao orderDao = new OrderDao();
            session.setAttribute(ConstantsJSP.USER, user);
            List<Order> orders = orderDao.findByUserId(user.getId());
            createOrderInfo(session, orders);
        } catch (DaoException e) {
            logger.error("Unable to set orderInfo!");
            throw new ServiceException(e.getMessage());
        }
    }

    private static void createOrderInfo(HttpSession session, List<Order> orders) throws ServiceException { //!!!!!!!!!!!
        try {
            IFlightDao flightDao = new FlightDao();
            List<OrderInfo> infos = new ArrayList<>();
            for (Order ord : orders) {
                Optional<Flight> optionalFlight = flightDao.findById(ord.getFlightId());
                ord.setTotalCost(calculateTotalCost(ord));
                optionalFlight.ifPresent(value -> infos.add(new OrderInfo(ord, value)));
            }
            System.out.println("createOrderInfo: " + infos);
            session.setAttribute(ConstantsJSP.ORDERS, infos);
        } catch (DaoException e) {
            logger.error("Unable to create orderInfo!");
            throw new ServiceException(e.getMessage());
        }
    }

    public static int calculateTotalCost(Order order) throws ServiceException {
        try {
            IFlightDao flightDao = new FlightDao();
            int totalCost = 0;
            Optional<Flight> optionalFlight = flightDao.findById(order.getFlightId());
            if (optionalFlight.isPresent()) {
                totalCost = optionalFlight.get().getPrice() * order.getAmount();
            }
            return totalCost;
        } catch (DaoException e) {
            logger.error("Unable to calculate total cost!");
            throw new ServiceException(e.getMessage());
        }
    }
}
