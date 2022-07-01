import gql from "graphql-tag";

export const UPDATE_AGENT = gql`mutation ($input: updateAgent!) {
        updateAgent(input:$input){
            agentCode
        }
    }`;

export const CREATE_ORDER = gql`mutation ($input: createOrder!) {
    createOrder(input:$input){
        ordNum
        ordAmount
        advanceAmount
        ordDate
        ordDescription
        agent{
            agentCode
        }
        customer{
            custCode
        }
    }
}`;

export const DELETE_ORDER = gql`mutation ($input: Int!) {
    deleteOrder(ordNum: $input)
}`;

export const DELETE_CUSTOMER_WITH_NO_ORDER = gql`mutation ($custCode: String!) {
    deleteCustomerWithNoOrder(custCode:$custCode)
}`;