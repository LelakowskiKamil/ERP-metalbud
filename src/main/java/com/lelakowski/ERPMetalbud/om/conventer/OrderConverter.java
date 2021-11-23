package com.lelakowski.ERPMetalbud.om.conventer;

import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrder;
import com.lelakowski.ERPMetalbud.om.domain.model.ProductOrderItem;
import com.lelakowski.ERPMetalbud.om.web.command.CreateOrderCommand;
import com.lelakowski.ERPMetalbud.pim.domain.model.Account;
import com.lelakowski.ERPMetalbud.pim.domain.model.Customer;
import com.lelakowski.ERPMetalbud.pim.domain.model.Privileges;
import com.lelakowski.ERPMetalbud.pim.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final CustomerRepository customerRepository;
    private final OrderItemConverter orderItemConverter;


    public ProductOrder from(CreateOrderCommand createOrderCommand) {
        if (createOrderCommand == null) throw new IllegalArgumentException();


        List<ProductOrderItem> productOrderItems = createOrderCommand.getOrderItems().stream()
                .map(orderItemConverter::from)
                .collect(Collectors.toList());

        //TODO validation does the customer with the given id exist
        Customer customer = customerRepository.getOne(createOrderCommand.getCustomerId());
        Privileges customerPrivileges = getPrivilegesForCustomer(customer);

        if (!customerPrivileges.getCanCreate()) throw new IllegalArgumentException();

        return ProductOrder.builder()
                .orderDate(createOrderCommand.getOrderDate())
                .customer(customer)
                .productOrderItems(productOrderItems)
                .build();
    }

    private Privileges getPrivilegesForCustomer(Customer customer) {
        Account customerAccount = customer.getAccount();
        return customerAccount.getPrivileges();
    }

}
