import React, { useState, useEffect } from "react";
import Navbar from "./Navbar";
import { Tabs, TabList, TabPanels, Tab, TabPanel } from "@chakra-ui/react";
import Courier from "./Courier";
import UserCurrent from "./UserCurrent";
import UserPrevious from "./UserPrevious";
import AdminOngoing from "./AdminOngoing";
import AdminCompleted from "./AdminCompleted";
import AdminOutForDelivery from "./AdminOutForDelivery";

function Admin() {
  const [user, setUser] = useState("");

  useEffect(() => {
    setUser(sessionStorage.getItem("adminName"));
  }, [user]);

  return user != "" ? (
    <div>
      <Navbar name={user} />
      <Tabs variant='unstyled'>
        <TabList>
          <Tab _selected={{ color: "white", bg: "blue.500" }}>Ongoing</Tab>
          <Tab _selected={{ color: "white", bg: "purple.500" }}>
            Out For Delivery
          </Tab>
          <Tab _selected={{ color: "white", bg: "orange.400" }}>Completed</Tab>
        </TabList>
        <TabPanels>
          <TabPanel>
            <AdminOngoing />
          </TabPanel>
          <TabPanel>
            <AdminOutForDelivery />
          </TabPanel>
          <TabPanel>
            <AdminCompleted />
          </TabPanel>
        </TabPanels>
      </Tabs>
    </div>
  ) : (
    <>Login Please</>
  );
}

export default Admin;
