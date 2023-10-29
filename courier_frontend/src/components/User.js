import React, { useState, useEffect } from "react";
import Navbar from "./Navbar";
import { Tabs, TabList, TabPanels, Tab, TabPanel } from "@chakra-ui/react";
import Courier from "./Courier";
import UserCurrent from "./UserCurrent";
import UserPrevious from "./UserPrevious";

function User() {
  const [user, setUser] = useState("");

  useEffect(() => {
    setUser(sessionStorage.getItem("name"));
  }, [user]);

  return user != "" ? (
    <div>
      <Navbar name={user} />
      <Tabs variant='unstyled'>
        <TabList>
          <Tab _selected={{ color: "white", bg: "blue.500" }}>New Courier</Tab>
          <Tab _selected={{ color: "white", bg: "green.400" }}>Current</Tab>
          <Tab _selected={{ color: "white", bg: "orange.400" }}>Previous</Tab>
        </TabList>
        <TabPanels>
          <TabPanel>
            <Courier />
          </TabPanel>
          <TabPanel>
            <UserCurrent />
          </TabPanel>
          <TabPanel>
            <UserPrevious />
          </TabPanel>
        </TabPanels>
      </Tabs>
    </div>
  ) : (
    <>Login Please</>
  );
}

export default User;
