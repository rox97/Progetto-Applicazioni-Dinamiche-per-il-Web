import gql from "graphql-tag";

export const ALL_ORDERS = gql`
    {
        allOrders {
            ordNum
            ordAmount
            advanceAmount
            ordDate
            ordDescription
            agent {
                agentCode
            }
            customer {
                custCode
            }
        }
    }
`;

export const ALL_AGENTS = gql`
    {
        allAgents {
            agentCode
            agentName
            workingArea
            commission
            phoneNo
            country
        }
    }
`;

export const ALL_CUSTOMERS = gql`
    {
        allCustomers {
            custCode
            custName
            custCity
            workingArea
            custCountry
            grade
            openingAmt
            receiveAmt
            paymentAmt
            outstandingAmt
            phoneNo
            agent{
                agentCode
            }
        }
    }
`;

export const CUSTOMER_BY_CUST_CODE = gql`
    query ($custCode: String!){
        customerByCustCode(custCode: $custCode){
            custCode
            custName
            custCity
            workingArea
            custCountry
            grade
            openingAmt
            receiveAmt
            paymentAmt
            outstandingAmt
            phoneNo
            agent{
                agentCode
            }
        }
        
    }
    `;
export const ORDER_BY_ORD_NUM = gql`
    query ($ordNum: Int!) {
        orderByOrdNum(ordNum: $ordNum) {
            ordNum
            ordAmount
            advanceAmount
            ordDate
            ordDescription
        }
    }
`;
export const EXISTS_ORDER_BY_ORD_NUM = gql`
    query ($ordNum: Int!) {
        existsOrderByOrdNum(ordNum: $ordNum)
    }
`;

export const ORDERS_BY_CUST_CODE = gql`
    query ($custCode: String!) {
        ordersByCustCode(custCode: $custCode) {
            ordNum
            ordAmount
            advanceAmount
            ordDate
            ordDescription
            agent{
                agentCode
            }
        }
    }
`;

export const ORDERS_BY_AGENT_CODE = gql`
    query ($agentCode: String!) {
        ordersByAgentCode(agentCode: $agentCode) {
            ordNum
            ordAmount
            advanceAmount
            ordDate
            ordDescription
            customer{
                custCode
            }
        }
    }
`;

export const AGENT_BY_AGENT_CODE = gql`
    query ($agentCode: String!) {
        agentByAgentCode(agentCode: $agentCode) {
            agentCode
            agentName
            workingArea
            commission
            phoneNo
            country
        }
    }
`