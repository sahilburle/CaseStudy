import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Form, FormGroup, Input, Label } from "reactstrap";
import Website from "../../api/website";

const ModifyProduct = (props) => {
  
  const fetchAllProducts = () => {
    axios.get(`${Website}/products/allproducts`).then(
      (Response) => {
        console.log(Response.data);
        setallProducts(Response.data);
      },
      (Error) => {
        console.log(Error);
      }
    );
  };

  useEffect(() => {
    fetchAllProducts();
    console.log(Product);
  }, []);

  const getProductByName = (productName) => {
    console.log(productName);
    axios.get(`${Website}/products/single/${productName}`).then(
      (Response) => {
        localStorage.setItem("prod",JSON.stringify(Response.data));
        setProduct(JSON.parse(localStorage.getItem("prod")))
        console.log(Product)

        document.getElementById("Id").value = Response.data.productId;
        document.getElementById("Type").value = Response.data.productType;
        document.getElementById("Category").value = Response.data.category;
        document.getElementById("Price").value = Response.data.price;
        document.getElementById("Description").value = Response.data.description;
      },
      (Error) => {
        console.log(Error);
      }
    );
  };

  const [allProduct, setallProducts] = useState([]);

  const handleForm = (event) => {
    console.log(Product);
    postDataToServer(Product);
    event.preventDefault();
  };

  const postDataToServer = (data) => {
    axios.put(`${Website}/products/update/{id}`,data).then(
        (Response) => {
            alert("Sucess")               
        },
        (Error) => {
            alert("Error")
        }        
    )
}

  const [Product, setProduct] = useState({
    productId: "",

    productName: "",

    productType: "",

    price: "",

    category: "",

    description: "",

    image: "",
  });

  const {
    productId,

    productName,

    productType,

    price,

    category,

    description,

    image,
  } = Product;

  return (
    <div className="m-auto w-50 shadow border mt-3 text-center p-3">
      <Form onSubmit={handleForm}>
        <h1>Update Product</h1>
        <FormGroup>
          <Label for="dropdown-basic-button">Product Name</Label>
          <Input
            type="select"
            name="Role"
            id="Name"
            onChange={(event) => {
              setProduct({ ...Product, productName: event.target.value });
              getProductByName(event.target.value);
            }}
          >
            {allProduct.length > 0
              ? allProduct.map((product) => (
                  <option value={product.productName}>
                    {product.productName}
                  </option>
                ))
              : "No products"}
          </Input>
        </FormGroup>

        <FormGroup>
          <Label> Product Id </Label>
          <Input
            type="text"
            placeholder="Product Id"
            name="Id"
            id="Id"
            // value={Product.productId}
             onChange={(event) => {
              setProduct({ ...Product, productId: event.target.value });
            }}
          />
        </FormGroup>

        <FormGroup>
          <Label> Product Type </Label>
          <Input
            type="text"
            placeholder="Product Type"
            name="Product Type"
            id="Type"
            value={Product.productType}
            onChange={(event) => {
              setProduct({ ...Product, productType: event.target.value });
            }}
          />
        </FormGroup>

        <FormGroup>
          <Label> Product Category </Label>
          <Input
            type="text"
            placeholder="Enter Product Category"
            name="Product Category"
            id="Category"
            onChange={(event) => {
              setProduct({ ...Product, category: event.target.value });
            }}
          />
        </FormGroup>

        <FormGroup>
          <Label> Product Price </Label>
          <Input
            type="text"
            placeholder="Enter Product Price"
            name="Product Price"
            id="Price"
            onChange={(event) => {
              setProduct({ ...Product, price: event.target.value });
            }}
          />
        </FormGroup>

        <FormGroup>
          <Label> Product Description </Label>
          <Input
            type="textarea"
            placeholder="Product Description"
            name="Product Description"
            id="Description"
            onChange={(event) => {
              setProduct({ ...Product, description: event.target.value });
            }}
          />
        </FormGroup>

        <Button
          className="btn btn-block m-2 w-25"
          type="submit"
          color="success"
        >
          Update Product
        </Button>

        <Button className="btn btn-block m-2 w-25" type="reset" color="danger">
          Clear
        </Button>
      </Form>
    </div>
  );
};

export default ModifyProduct;