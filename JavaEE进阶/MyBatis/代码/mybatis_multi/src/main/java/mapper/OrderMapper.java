package mapper;

import domain.Order;

import java.util.List;

/**
 * @author
 * @Program
 * @create 2022-03-22-12:23
 */
public interface OrderMapper {
    public List<Order> findAll();
}
