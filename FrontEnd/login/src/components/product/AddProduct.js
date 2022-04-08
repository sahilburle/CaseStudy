import axios from "axios";
import React, { useEffect, useState } from "react";
import { Alert } from "react-bootstrap";
import { toast } from "react-toastify";
import { Button, Form, FormGroup, Input, Label } from "reactstrap";
import Website from "../../api/website";

const AddProduct=() => {

    useEffect(() => {
        document.title = "AddProduct";
      }, []);

    const [Product, setProduct] = useState({});

    const handleForm = (event) => {
        console.log(Product);
        postDataToServer(Product);
        event.preventDefault();
      };

    const postDataToServer = (data) => {
        axios.post(`${Website}/products/addproducts`,data).then(
            (Response) => {
                alert("Sucess")               
            },
            (Error) => {
                alert("Error")
            }        
        )
    }

    const formData = new FormData();

    const imgupload = (img) => (e) => {
        const imgfile = e.target.files[0];
        formData.set(img, imgfile);
        console.log(formData);
        axios.post(`${Website}/products/upload-image`, formData).then(
            (Response) => {
                console.log(Response);
                setProduct({ ...Product, image: Response.data});
                alert("uploaded");
            },
            (Error) => {
                alert(Error);               
            }
        );
    };
    

    return (

        <div className="m-auto w-50 border shadow mt-3 text-center p-3">
            <Form onSubmit={handleForm}>
                <h1>Add Product</h1>

                <FormGroup>
                    <Label style={{ color: 'blue' }}> Product Image </Label>
                    <Input
                        type="file"
                        name="file"
                        id="Image"
                        onChange={imgupload("file")}
                    />
                </FormGroup>
                <FormGroup>
                    <Label style={{ color: 'blue' }}> Product Name </Label>
                    <Input
                        type="text"
                        placeholder="Product Name"
                        name="Product Name"
                        id="Name"
                        onChange={(event) => {
                            setProduct({...Product, productName: event.target.value});
                          }}
                    />
                </FormGroup>

                <FormGroup>
                    <Label style={{ color: 'blue' }}> Product Type </Label>
                    <Input
                        type="text"
                        placeholder="Product Type"
                        name="Product Type"
                        id="Type"
                        onChange={(event) => {
                            setProduct({...Product, productType: event.target.value});
                          }}
                    />
                </FormGroup>

                <FormGroup>
                    <Label style={{ color: 'blue' }}> Product Category </Label>
                    <Input
                        type="text"
                        placeholder="Enter Product Category"
                        name="Product Category"
                        id="Category"
                        onChange={(event) => {
                            setProduct({...Product, category: event.target.value});
                          }}
                    />
                </FormGroup>

                <FormGroup style={{ color: 'blue' }}>
                    <Label> Product Price </Label>
                    <Input
                        type="text"
                        placeholder="Enter Product Price"
                        name="Product Price"
                        id="Price"
                        onChange={(event) => {
                            setProduct({...Product, price: event.target.value});
                          }}
                    />
                </FormGroup>

                <FormGroup>
                    <Label style={{ color: 'blue', fontWeight: "bolder"}}> Product Description </Label>
                    <Input
                        type="textarea"
                        placeholder="Product Description"
                        name="Product Description"
                        id="Description"
                        onChange={(event) => {
                            setProduct({...Product, description: event.target.value});
                          }}
                    />
                </FormGroup>

                <Button 
                    className="btn btn-block m-2 w-25"
                    type="submit" 
                    color="success"
                > 
                    Add Product
                </Button>

                <Button 
                    className="btn btn-block m-2 w-25" 
                    type="reset" 
                    color="danger"
                > 
                    Clear
                </Button>

            </Form>
        </div>
    )
}

export default AddProduct;