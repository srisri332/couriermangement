import React, { useState, useEffect } from "react";
import { Text } from "@chakra-ui/react";
import {
  FormControl,
  FormLabel,
  Input,
  RadioGroup,
  HStack,
  Radio,
  Select,
  Button,
  AlertDialog,
  AlertDialogBody,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogContent,
  AlertDialogOverlay,
  useDisclosure,
  useToast,
} from "@chakra-ui/react";
import axios from "axios";
import AddReceiver from "./AddReceiver";

function Courier() {
  const [receivers, setReceivers] = useState([]);
  const [description, setDescription] = useState(null);
  const [fragile, setFragile] = useState(null);
  const [weight, setWeight] = useState(0);
  const [receiverDetails, setReceiverDetails] = useState(null);
  const [amount, setAmount] = useState(0);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const cancelRef = React.useRef();
  const toast = useToast();

  const request = {
    orderPackage: {
      description: description,
      isFragile: fragile * 1,
      weight: weight,
    },
    sender: {
      senderID: sessionStorage.getItem("senderID") * 1,
      name: sessionStorage.getItem("name"),
      email: sessionStorage.getItem("email"),
      phone: sessionStorage.getItem("phone") * 1,
      address: sessionStorage.getItem("address"),
    },
    receiver: receivers.filter((receiver) => {
      return receiver.receiverID == receiverDetails;
    })[0],
    payment: {
      type: "Cash",
      amount: amount,
      senderID: sessionStorage.getItem("senderID") * 1,
    },
  };

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/getreceivers/${sessionStorage.getItem(
          "senderID"
        )}`
      )
      .then((response) => {
        setReceivers(response.data);
      });
  }, [description]);

  function processOrder() {
    axios
      .post("http://localhost:8080/processorder", request)
      .then((response) => {
        // console.log(response);
        if (response.data === "Success") {
          show("Success");
        } else {
          show(null);
        }
      });
    setDescription("");
    setFragile(null);
    setWeight(0);
    // console.log(request);
  }

  const show = (text) => {
    if (text != null) {
      toast({
        title: "Order processed",
        description: `Successfully processed order`,
        status: "success",
        duration: 5000,
        isClosable: true,
      });
    } else {
      toast({
        title: "Error",
        description: `Some Error Occured`,
        status: "error",
        duration: 5000,
        isClosable: true,
      });
    }
  };

  function AlertDialogExample() {
    setAmount(weight * 2);

    return (
      <>
        <Button
          type='submit'
          isDisabled={
            description == null ||
            fragile == null ||
            weight == 0 ||
            receiverDetails == undefined
          }
          colorScheme='green'
          onClick={onOpen}>
          Calculate Amount
        </Button>

        <AlertDialog
          isOpen={isOpen}
          leastDestructiveRef={cancelRef}
          onClose={onClose}>
          <AlertDialogOverlay>
            <AlertDialogContent>
              <AlertDialogHeader fontSize='lg' fontWeight='bold'>
                Review Payment
              </AlertDialogHeader>

              <AlertDialogBody>
                Make Payment of {amount} and Proceed?
              </AlertDialogBody>

              <AlertDialogFooter>
                <Button ref={cancelRef} onClick={onClose}>
                  Cancel
                </Button>
                <Button colorScheme='blue' onClick={onClose} ml={3}>
                  <p onClick={processOrder}>Proceed</p>
                </Button>
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialogOverlay>
        </AlertDialog>
      </>
    );
  }

  return (
    <div>
      <form>
        <Text>Package Details</Text>
        <FormControl isRequired>
          <FormLabel>Description</FormLabel>
          <Input
            type='text'
            onChange={(e) => setDescription(e.target.value)}
            style={{ margin: 10 }}
          />
        </FormControl>
        <FormControl as='fieldset' isRequired>
          <FormLabel as='legend'>Is Fragile</FormLabel>
          <RadioGroup defaultValue='No'>
            <HStack spacing='24px' style={{ margin: 10 }}>
              <Radio value='1' onChange={(e) => setFragile(e.target.value * 1)}>
                Yes
              </Radio>
              <Radio value='0' onChange={(e) => setFragile(e.target.value * 1)}>
                No
              </Radio>
            </HStack>
          </RadioGroup>
        </FormControl>
        <FormControl isRequired>
          <FormLabel>Weight</FormLabel>
          <Input
            type='number'
            style={{ margin: 10 }}
            onChange={(e) => {
              setWeight(e.target.value * 1);
            }}
          />
        </FormControl>
      </form>
      <br />
      <form>
        <Text>Receiver Details</Text>
        <FormControl isRequired>
          <FormLabel>Select Receiver</FormLabel>
          <Select
            placeholder='select receiver'
            style={{ margin: 10 }}
            onChange={(e) => setReceiverDetails(e.target.value)}>
            {receivers.map((receiver) => {
              return (
                <option key={receiver.receiverID} value={receiver.receiverID}>
                  {receiver.name} || {receiver.address} || {receiver.email}
                </option>
              );
            })}
          </Select>
        </FormControl>
      </form>
      <br />
      <div>
        <AddReceiver /> <AlertDialogExample />
      </div>
    </div>
  );
}

export default Courier;
