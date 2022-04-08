import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import {
  Card,
  CardBody,
  CardHeader,
  Form,
  FormGroup,
  Button,
} from "reactstrap";
import Website from "../../api/website";
import Menu from "../Menu";

function AllProducts() {
  const value = localStorage.getItem("role");
  const [items, setItems] = useState({
    itemsList: [],
  });

  var arr = [];
  var userid;

  const addToCart = (item) => {
    arr = items.itemsList;
    arr.push({
      productName: item.productName,
      quantity: 1,
      image: item.image,
    });

    setItems({ ...items, itemsList: arr });
    console.log(items);
    userid = JSON.parse(localStorage.getItem("user")).userId.profileId;

    axios.post(`${Website}/mycart/addCart/${userid}`, items).then(
      (Response) => {
        console.log(Response.data);
      },
      (Error) => {
        console.log(Error);
      }
    );
  };

  const delbyid = (proid) => {
    axios.delete(`${Website}/products/delete/${proid}`).then(
      (Response) => {
        console.log(Response);
        removeProductById(proid)
        alert("Deleted");
      },
      (Error) => {
        alert("Not deleted");
      }
    );
  };

  const removeProductById = (id) => {
    setAllProducts(Products.filter((c) => c.productId !== id));
  };

  console.log(value);
  useEffect(() => {
    document.title = "All Products";
  }, []);

  const fetchAllProducts = () => {
    axios.get(`${Website}/products/allproducts`).then(
      (Response) => {
        console.log(Response.data);
        setAllProducts(Response.data);
      },
      (Error) => {
        console.log(Error);
      }
    );
  };

  useEffect(() => {
    fetchAllProducts();
  }, []);

  const [Products, setAllProducts] = useState([]);
  const [allProduct, setallProducts] = useState({ view: false });

  return (
    <div className="m-auto  text-center p-3 row" style={{backgroundImage: `url("./background.jpg")`}}>
      <div class="col-3">
        {value === "[ROLE_Merchant]" && (
          <div>
            <ul class="list-group">
              <Link class="list-group-item" to="/addproduct" action>
                {" "}
                Add Product{" "}
              </Link>
              <Link class="list-group-item" to="/updateproduct" action>
                {" "}
                Modify Product{" "}
              </Link>
            </ul>
          </div>
        )}
      </div>

      {allProduct && (
        <div 
          class="col-9"
          >
          <Card >
            <CardHeader tag="h3">All Products</CardHeader>
            <CardBody style={{backgroundColor: "#1A132F"}}>
              <ul class="list-group row m-auto w-75 ">
                {Products.length > 0
                  ? Products.map((product) => (
                      <li
                        class="list-group-item  m-3 p-3 rounded shadow-sm"
                        //style={{backgroundColor: "#EEEEEE"}}
                         //m-2 ml-5 ml-lg-0`
                        key={product.productId}
                      >
                        <img
                          src={product.image}
                          align="left"
                          alt="picture"
                          style={{ height: "170px", maxWidth: "170px"}}
                        />{" "}
                        {/* <br /> */}
                        {product.productName} <br />
                        {product.price} <br />
                        {product.productType} <br />
                        {product.category} <br />
                        {product.description} <br />
                        <br />
                        {localStorage.getItem("role") === "[ROLE_Customer]" && (
                          <Button
                            className="center"
                            type="submit"
                            color="success"
                            onClick={() => {
                              addToCart(product);
                            }}
                          >
                            Add to Cart
                          </Button>
                        )}
                        {localStorage.getItem("role") === "[ROLE_Merchant]" && (
                          <Button
                            className="center"
                            type="submit"
                            color="danger"
                            onClick={() => {
                              delbyid(product.productId);
                            }}
                          >
                            Delete
                          </Button>
                        )}
                      </li>
                    ))
                  : "No Products"}
              </ul>
            </CardBody>
          </Card>
        </div>
      )}
    </div>
  );
}

export default AllProducts;