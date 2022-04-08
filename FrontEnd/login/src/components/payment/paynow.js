import axios from "axios";
import React from "react";
import { useEffect , useState} from "react"
import { Button } from "reactstrap";
import Website from "../../api/website";

const Paynow = () => {
  useEffect(() => {
    document.title = "PayNow";

    setStripeDetails({
      ...stripeDetails,
      token: localStorage.getItem("token"),
    });

    setStripeDetails({
      ...stripeDetails,
      stripe: window.Stripe(stripeApiToken),
    });
  }, []);

  const [stripeDetails, setStripeDetails] = useState({
    stripeApiToken:
      "pk_test_51Kh66cSDPmgfeiwfBQAKDiv1SWwOMPAl72dLRgJoz8UJ0YmaWyyEqNdU7GLtxlzz63F20DL3aYN7ZyIWb0SCmeha00d80D6ABY",

    stripe: "",

    token: null,
  });

  let checkOutBodyArray = [];

  const { stripeApiToken, stripe, token } = stripeDetails;

  const goToCheckout = () => {
    checkOutBodyArray = [];

    getItems();

    console.log(checkOutBodyArray);

    axios
      .post(`${Website}/paymentStripe/create-checkout-session`, checkOutBodyArray)

      .then(
        (response) => {
          localStorage.setItem("sessionId", response.data.sessionId);

          console.log("session");

          stripe.redirectToCheckout({ sessionId: response.data.sessionId });
        },

        (error) => {
          console.log(error);
        }
      );
  };

  const getItems = () => {
    let orderId = (localStorage.getItem("orderId"));

    console.log(orderId);

    let orders = JSON.parse(localStorage.getItem("order")).filter(
      (order) => order.orderId === orderId
    );

    let products = orders[0].itemsList;

    products.map((product) => {
      checkOutBodyArray.push({
        price: product.price,

        quantity: product.quantity,

        productId: product.productId,

        productName: product.productName,

        userId: orders[0].customerId,
      });
    });
  };

  return (
    <div>
      <div className="text-center">
        <div class="alert alert-warning w-75 m-auto text-center">
          {" "}
          While making payment use card number 4242 4242 4242 4242 and enter
          random data and cvv(3 digit)
        </div>

        <Button
          type="submit"
          className="btn btn-outline-success rounded btn-block m-3 w-25"
          color="light"
          // to="/checkout"\

          // onClick={goToCheckout}

          onClick={() => {
            goToCheckout();
          }}
        >
          Make Payment
        </Button>
      </div>
    </div>
  );
};

export default Paynow;
