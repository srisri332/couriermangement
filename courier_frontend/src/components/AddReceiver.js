import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Button,
  FormControl,
  FormLabel,
  Input,
} from "@chakra-ui/react";
import axios from "axios";
import { useState } from "react";

export default function AddReceiver() {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [name, setName] = useState("");
  const [mail, setMail] = useState("");
  const [phone, setPhone] = useState(0);
  const [address, setAdd] = useState("");

  const request = {
    name: name,
    email: mail,
    phone: phone,
    address: address,
    senderID: sessionStorage.getItem("senderID") * 1,
  };

  function addReceiver() {
    axios
      .post("http://localhost:8080/addreceiver", request)
      .then((response) => {
        console.log(response.data);
      });
    //     console.log(request);
  }

  return (
    <>
      <Button colorScheme='blue' onClick={onOpen}>
        Add Receiver
      </Button>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Add Receiver</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <FormControl isRequired>
              <FormLabel>Name</FormLabel>
              <Input
                type='text'
                style={{ margin: 10 }}
                onChange={(e) => setName(e.target.value)}
              />
            </FormControl>
            <FormControl isRequired>
              <FormLabel>Email</FormLabel>
              <Input
                type='email'
                style={{ margin: 10 }}
                onChange={(e) => setMail(e.target.value)}
              />
            </FormControl>
            <FormControl isRequired>
              <FormLabel>Address</FormLabel>
              <Input
                type='text'
                style={{ margin: 10 }}
                onChange={(e) => setAdd(e.target.value)}
              />
            </FormControl>
            <FormControl isRequired>
              <FormLabel>Phone</FormLabel>
              <Input
                type='number'
                style={{ margin: 10 }}
                onChange={(e) => setPhone(e.target.value)}
              />
            </FormControl>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme='ghost' mr={3} onClick={onClose}>
              Close
            </Button>
            <Button colorScheme='blue' onClick={addReceiver}>
              <p onClick={onClose}>Add</p>
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
}
