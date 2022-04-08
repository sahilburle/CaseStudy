import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Button } from "reactstrap";
import Website from "../api/website";

const Order = () => {
  var allproduct = JSON.parse(localStorage.getItem("order"));

  useEffect(() => {
    //console.log("show")
    getORdersfromDB(userId);
    var allproduct = JSON.parse(localStorage.getItem("order"));
    console.log("show");
  }, []);

  var userId = JSON.parse(localStorage.getItem("user")).userId.profileId;

  const delbyid = (Name) => {
    // let cart = [];

    axios.delete(`${Website}/order/deleteOrder/${Name}`).then(
      (Response) => {
        console.log(Response);
        alert("Order deleted, please reload the page");
        
      },
      (Error) => {
        alert("Not deleted");
      }
    );
  };

  console.log(allproduct);

  return (
    <div className="container">
      <div>
        {allproduct.length > 0
          ? allproduct.map((product) => (
              <div
                class="card m-3 shadow"
                style={{
                  //height: "170px",
                  //maxWidth: "540px",
                  backgroundColor: "#D3DEDC", //#D3DEDC
                }}
              >
                <div class="row p-3">
                  <div class="col-sm-5">
                    <div class="card">
                      <div class="card-body">
                        <h5 class="card-title" style={{ color: "red" }}>
                          Order Details
                        </h5>
                        <p class="card-text">Order Id : {product.orderId}</p>
                        <p class="card-text">Amount : {product.amountPaid}</p>
                        <p class="card-text">
                          Customer Id : {product.customerId}
                        </p>
                        <p class="card-text">
                          Order Status : {product.orderStatus}
                        </p>
                        <p class="card-text">Quantity : {product.quantity}</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-7">
                    <div class="card">
                      <div class="card-body ">
                        <h5 class="card-title" style={{ color: "red" }}>
                          {" "}
                          Products{" "}
                        </h5>
                        <div
                          className=" overflow-auto p-2 m-auto"
                          style={{
                            height: "184px",
                            maxWidth: "530px",
                          }}
                        >
                          {product.itemsList.length > 0
                            ? product.itemsList.map((product) => (
                                <div
                                  class="card mb-3 shadow-sm m-auto"
                                  style={{
                                    height: "170px",
                                    // width: "400px",
                                    backgroundColor: "#AEFEFF",
                                  }}
                                >
                                  <div class="row g-0">
                                    <div class="col-md-4">
                                      <img
                                        src={product.image}
                                        class="img-fluid rounded-start"
                                        alt="picture"
                                        style={{
                                          height: "170px",
                                          maxWidth: "160px",
                                        }}
                                      />
                                    </div>

                                    <div class="col-md-8">
                                      <div class="card-body text-center">
                                        <h5 class="card-title">
                                          {product.productName}
                                        </h5>
                                        <p class="card-text">
                                          Quantity : {product.quantity} <br />
                                          Price : {product.price}
                                        </p>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              ))
                            : "Empty Cart"}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row g-0 m-auto">
                  {/* <div class="col-md-8">
                    <div class="card-body">
                      
                    </div>
                  </div> */}
                  <div className="mb-3">
                    <Link
                      className="btn rounded text-light btn-sm px-4 me-2"
                      tag="a"
                      to="/pay"
                      style={{
                        //height: "170px",
                        //maxWidth: "540px",
                        backgroundColor: "#781C68", //#D3DEDC
                      }}
                      onClick={() =>
                        localStorage.setItem("orderId", product.orderId)
                      }
                      action
                    >
                      Proceed to Payment
                    </Link>
                    <Button
                      className="center"
                      type="submit"
                      color="danger"
                      onClick={() => {
                        delbyid(product.orderId);
                      }}
                    >
                      Delete
                    </Button>
                  </div>
                </div>
              </div>
            ))
          : "No Orders Placed"}
      </div>
    </div>
  );
};

export default Order;

export const getORdersfromDB = (id) => {
  axios.get(`${Website}/order/customer/${id}`).then(
    (Response) => {
      console.log(Response);

      localStorage.setItem("order", JSON.stringify(Response.data));
    },
    (Error) => {
      console.log(Error.data);
      alert("something went wrong");
    }
  );
  console.log("showing");
};
