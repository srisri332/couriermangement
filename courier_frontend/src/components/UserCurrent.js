import axios from "axios";
import React, { useEffect, useState } from "react";
import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Text,
  Heading,
  Stack,
  StackDivider,
  Box,
} from "@chakra-ui/react";

function UserCurrent() {
  const [currentorders, setcurrentorders] = useState([]);

  const request = {
    sender: {
      senderID: sessionStorage.getItem("senderID") * 1,
      name: sessionStorage.getItem("name"),
      email: sessionStorage.getItem("email"),
      phone: sessionStorage.getItem("phone") * 1,
      address: sessionStorage.getItem("address"),
    },
  };

  useEffect(() => {
    axios
      .post("http://localhost:8080/currentorders", request)
      .then((response) => {
        setcurrentorders(response.data.details);
      });
  }, []);

  return (
    <div>
      {currentorders != undefined && currentorders.length != 0 ? (
        currentorders.map((delivery) => {
          return (
            <Card margin={10} key={delivery.deliveryID}>
              <CardHeader>
                <Heading size='md'>Delivery {delivery.deliveryID}</Heading>
              </CardHeader>

              <CardBody>
                <Stack divider={<StackDivider />} spacing='4'>
                  <Box>
                    <Heading size='xs' textTransform='uppercase'>
                      Status
                    </Heading>
                    <Text pt='2' fontSize='sm'>
                      {delivery.deliveryStatus}
                    </Text>
                  </Box>
                  <Box>
                    <Heading size='xs' textTransform='uppercase'>
                      Package Details
                    </Heading>
                    <Text pt='2' fontSize='sm'>
                      {delivery.pkgDescription} || {delivery.pkgWeight} ||{" "}
                      {delivery.pkgFragile}
                    </Text>
                  </Box>
                  <Box>
                    <Heading size='xs' textTransform='uppercase'>
                      Receiver Details
                    </Heading>
                    <Text pt='2' fontSize='sm'>
                      {delivery.receiverName} || {delivery.receiverEmail} ||{" "}
                      {delivery.receiverPhn} || {delivery.receiverAdd}
                    </Text>
                  </Box>
                </Stack>
              </CardBody>
            </Card>
          );
        })
      ) : (
        <p>Nothing to Display</p>
      )}
    </div>
  );
}

export default UserCurrent;
