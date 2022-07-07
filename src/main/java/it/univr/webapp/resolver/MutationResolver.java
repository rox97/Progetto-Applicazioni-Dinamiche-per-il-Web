package it.univr.webapp.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import it.univr.webapp.input.CreateOrder;
import it.univr.webapp.input.UpdateAgent;
import it.univr.webapp.input.UpdateCustomer;
import it.univr.webapp.input.UpdateOrder;
import it.univr.webapp.models.orders.AgentsEntity;
import it.univr.webapp.models.orders.CustomerEntity;
import it.univr.webapp.models.orders.OrdersEntity;
import it.univr.webapp.repositories.orders.AgentsRepository;
import it.univr.webapp.repositories.orders.CustomersRepository;
import it.univr.webapp.repositories.orders.OrderRepository;
import lombok.AllArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private AgentsRepository agentsRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomersRepository customersRepository;

    ////////////////////////////////////////AGENTS MUTATION////////////////////////////////////////

    @Transactional
    public AgentsEntity updateAgent(UpdateAgent input){
        AgentsEntity agent = agentsRepository.findById(input.getAgentCode()).get();

        if(input.getAgentName()!=null){
            agent.setAgentName(input.getAgentName());
        }
        if(input.getWorkingArea()!=null){
            agent.setWorkingArea(input.getWorkingArea());
        }
        if(input.getCommission()!=null){
            agent.setCommission(input.getCommission());
        }
        if(input.getPhoneNo()!=null){
            agent.setPhoneNo(input.getPhoneNo());
        }
        if(input.getCountry()!=null){
            agent.setCountry(input.getCountry());
        }

        return agent;
    }

    ////////////////////////////////////////ORDERS MUTATION////////////////////////////////////////

    @Transactional
    public OrdersEntity createOrder(CreateOrder input){

        OrdersEntity newOrder = new OrdersEntity(
                                    input.getOrdNum(),
                                    input.getOrdAmount(),
                                    input.getAdvanceAmount(),
                                    input.getOrdDate(),
                                    input.getOrdDescription(),
                                    agentsRepository.findById(input.getAgentCode()).get(),
                                    customersRepository.findById(input.getCustCode()).get()
                                );

        return orderRepository.saveAndFlush(newOrder);
    }

    @Transactional
    public int deleteOrder(int ordNum){
        return orderRepository.deleteByOrdNum(ordNum);
    }

    @Transactional
    public OrdersEntity updateOrder(UpdateOrder input){
        OrdersEntity order = orderRepository.findById(input.getOrdNum()).get();

        if(input.getOrdAmount()!=null){
            order.setOrdAmount(input.getOrdAmount());
        }
        if(input.getAdvanceAmount()!=null){
            order.setAdvanceAmount(input.getAdvanceAmount());
        }
        if(input.getOrdDate()!=null){
            order.setOrdDate(input.getOrdDate());
        }
        if(input.getOrdDescription()!=null) {
            order.setOrdDescription(input.getOrdDescription());
        }

        return order;
    }

    ////////////////////////////////////////CUSTOMER MUTATION////////////////////////////////////////

    @Transactional
    public CustomerEntity updateCustomer(UpdateCustomer input){
        CustomerEntity customer = customersRepository.findById(input.getCustCode()).get();

        if(input.getCustName()!=null){
            customer.setCustName(input.getCustName());
        }
        if(input.getCustCity()!=null){
            customer.setCustCity(input.getCustCity());
        }
        if(input.getWorkingArea()!=null){
            customer.setWorkingArea(input.getWorkingArea());
        }
        if(input.getCustCountry()!=null){
            customer.setCustCountry(input.getCustCountry());
        }
        if(input.getGrade()!=null){
            customer.setGrade(input.getGrade());
        }
        if(input.getOpeningAmt()!=null){
            customer.setOpeningAmt(input.getOpeningAmt());
        }
        if(input.getReceiveAmt()!=null){
            customer.setReceiveAmt(input.getReceiveAmt());
        }
        if(input.getPaymentAmt()!=null){
            customer.setPaymentAmt(input.getPaymentAmt());
        }
        if(input.getOutstandingAmt()!=null){
            customer.setOutstandingAmt(input.getOutstandingAmt());
        }
        if(input.getPhoneNo()!=null){
            customer.setPhoneNo(input.getPhoneNo());
        }

        return customer;
    }

}
