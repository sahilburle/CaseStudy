import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Card, CardBody, CardHeader } from "reactstrap";
import Website from "../api/website";
import { getORdersfromDB } from "./order";

function Cart() {
  const [product, setProduct] = useState({});

  
  const order = (cart) => {
    console.log(cart);
    axios.post(`${Website}/order/placeOrder`, cart).then(
      (Response) => {
        console.log(Response);
        getORdersfromDB(cart.cartId);
         //localStorage.setItem("order", Response.data);
        // alert("order placed successfully");
      },
      (Error) => {
        alert("something went wrong");
      }
    );
  };

  var userid;
  var db_cart;

  userid = JSON.parse(localStorage.getItem("user")).userId.profileId;

  useEffect(() => {
    axios.get(`${Website}/mycart/cart/${userid}`).then(
      (Response) => {
        localStorage.setItem("cart", JSON.stringify(Response.data));

        setProduct(JSON.parse(localStorage.getItem("cart")).itemsList);

        console.log(product);
      },
      (Error) => {
        console.log(Error.data);
        alert("something went wrong");
      }
    );
  }, []);

  const delbyid = (Name) => {
    let cart = [];

    if (typeof window !== undefined) {
      if (localStorage.getItem("cart")) {
        cart = JSON.parse(localStorage.getItem("cart"));
      }

      cart.itemsList.map((product, i) => {
        if (product.productName === Name) {
          // cart.totalPrice = cart.totalPrice - product.price * product.quantity;
          cart.itemsList.splice(i, 1);
        }
      });

      localStorage.setItem("cart", JSON.stringify(cart));

      setProduct(JSON.parse(localStorage.getItem("cart")).itemsList);
    }

    axios
      .put(`${Website}/mycart/update`, JSON.parse(localStorage.getItem("cart")))
      .then(
        (Response) => {
          console.log(Response);
          alert("Cart Updated");
        },
        (Error) => {
          alert("Not deleted");
        }
      );
  };

  const removeProductByName = (Name) => {
    setProduct(product.filter((c) => c.productName !== Name));
  };

  return (
    <div className="p-3 text-center">
      {product.length > 0
        ? product.map((product) => (
            <div
              class="card mb-3 shadow m-auto"
              style={{
                height: "171px",
                maxWidth: "540px",
                backgroundColor: "#BFFFF0",
              }}
            >
              <div class="row g-0">
                <div class="col-md-4">
                  <img
                    src={product.image}
                    class="img-fluid rounded-start"
                    alt="picture"
                    style={{ height: "169px", width: "160px" }}
                  />
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title">{product.productName}</h5>
                    <p class="card-text">{product.quantity}</p>
                  </div>
                  <Button
                    className="center"
                    type="submit"
                    color="danger"
                    onClick={() => {
                      delbyid(product.productName);
                    }}
                  >
                    Delete
                  </Button>
                </div>
              </div>
            </div>
          ))
        : "Empty Cart"}
        {/* <div></div> */}
      {product.length > 0 && (
        <Link
          className="btn btn-outline-dark rounded m-auto btn-sm px-4 me-2"
          tag="div"
          to="/order"
          onClick={() => order(JSON.parse(localStorage.getItem("cart")))}
          action
        >
          Proceed to Order
        </Link>
      )}
    </div>
  );
}

export default Cart;