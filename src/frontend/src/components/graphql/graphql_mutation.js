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

export const DELETE_ORDER = gql`mutation ($ordNum: Int!) {
    deleteOrder(ordNum: $ordNum)
}`;

export const UPDATE_ORDER = gql`mutation ($input: updateOrder!) {
    updateOrder(input:$input){
        ordNum
    }
}`;
export const UPDATE_CUSTOMER = gql`mutation ($input: updateCustomer!) {
    updateCustomer(input:$input){
        custCode
    }
}`;