import { useState } from "react";
import {
  Flex,
  Heading,
  Input,
  Button,
  InputGroup,
  Stack,
  InputLeftElement,
  chakra,
  Box,
  Link,
  Avatar,
  FormControl,
  FormHelperText,
  InputRightElement,
} from "@chakra-ui/react";
import { FaUserAlt, FaLock } from "react-icons/fa";
import { useToast } from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const CFaUserAlt = chakra(FaUserAlt);
const CFaLock = chakra(FaLock);

export default function Login() {
  const [showPassword, setShowPassword] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleShowClick = () => setShowPassword(!showPassword);
  const toast = useToast();

  const show = (name) => {
    if (name != null) {
      toast({
        title: "Login Successful",
        description: `Successfully logged in as ${name}`,
        status: "success",
        duration: 5000,
        isClosable: true,
      });
    } else {
      toast({
        title: "Login Error",
        description: `Wrong Credentials Entered`,
        status: "error",
        duration: 5000,
        isClosable: true,
      });
    }
  };

  function loginUser() {
    if (email.startsWith("admin")) {
      axios
        .post("http://localhost:8080/loginadmin", {
          email: email,
          password: password,
        })
        .then((response) => {
          if (response.data !== "") {
            sessionStorage.setItem("adminName", response.data.name);
            sessionStorage.setItem("adminEmail", response.data.email);
            sessionStorage.setItem("adminAddress", response.data.address);
            sessionStorage.setItem("adminID", response.data.senderID);
            sessionStorage.setItem("adminPhone", response.data.phone);
            show(sessionStorage.getItem("adminName"));
            navigate("/admin");
          } else {
            show(null);
          }
        });
    } else {
      axios
        .post("http://localhost:8080/loginsender", {
          email: email,
          password: password,
        })
        .then((response) => {
          if (response.data !== "") {
            sessionStorage.setItem("name", response.data.name);
            sessionStorage.setItem("email", response.data.email);
            sessionStorage.setItem("address", response.data.address);
            sessionStorage.setItem("senderID", response.data.senderID);
            sessionStorage.setItem("phone", response.data.phone);
            show(sessionStorage.getItem("name"));
            navigate("/user");
          } else {
            show(null);
          }
        });
    }
  }

  return (
    <Flex
      flexDirection='column'
      width='100wh'
      height='100vh'
      //       backgroundColor='gray.200'
      justifyContent='center'
      alignItems='center'>
      <Stack
        flexDir='column'
        mb='2'
        justifyContent='center'
        alignItems='center'>
        <Avatar bg='blue.500' />
        <Heading color='blue.400'>Welcome</Heading>
        <Box minW={{ base: "90%", md: "468px" }}>
          <form>
            <Stack spacing={4} p='1rem'>
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents='none'
                    children={<CFaUserAlt color='gray.300' />}
                  />
                  <Input
                    type='email'
                    placeholder='email address'
                    colorScheme='blue'
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </InputGroup>
              </FormControl>
              <FormControl>
                <InputGroup>
                  <InputLeftElement
                    pointerEvents='none'
                    color='gray.300'
                    children={<CFaLock color='gray.300' />}
                  />
                  <Input
                    type={showPassword ? "text" : "password"}
                    placeholder='Password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                  <InputRightElement width='4.5rem'>
                    <Button
                      h='1.75rem'
                      size='sm'
                      onClick={handleShowClick}
                      colorScheme='blue'>
                      {showPassword ? "Hide" : "Show"}
                    </Button>
                  </InputRightElement>
                </InputGroup>
              </FormControl>
              <Button
                borderRadius={0}
                onClick={loginUser}
                variant='solid'
                colorScheme='blue'
                width='full'>
                Login
              </Button>
            </Stack>
          </form>
        </Box>
      </Stack>
    </Flex>
  );
}
