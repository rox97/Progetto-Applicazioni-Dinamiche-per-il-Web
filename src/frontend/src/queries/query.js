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
