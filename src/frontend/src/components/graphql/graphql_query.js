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
`