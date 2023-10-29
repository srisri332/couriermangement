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
  Button,
} from "@chakra-ui/react";

function AdminOngoing() {
  const [currentorders, setcurrentorders] = useState([]);
  const [refreshData, setRefreshData] = useState(false);

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
      .get("http://localhost:8080/admin/current-deliveries", request)
      .then((response) => {
        console.log(response.data.details);
        setcurrentorders(response.data.details);
      });
  }, [refreshData]);

  function outForDelivery(delivery, e) {
    axios
      .get(`http://localhost:8080/admin/outfordelivery/${delivery.deliveryID}`)
      .then((response) => {
        console.log(response.data);
      });
  }

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
                      {delivery.pkgDescription} || {delivery.pkgWeight}
                      {delivery.pkgFragile == true ? <p>|| Fragile</p> : <></>}
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
              <Button
                borderRadius={0}
                onClick={(e) => {
                  outForDelivery(delivery, e);
                  setRefreshData(!refreshData);
                }}
                variant='solid'
                colorScheme='blue'
                width='full'>
                Out For Delivery
              </Button>
            </Card>
          );
        })
      ) : (
        <p>Nothing to display</p>
      )}
    </div>
  );
}

export default AdminOngoing;
