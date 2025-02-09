import EventList from "./components/EventList";

import {
  useMantineColorScheme,
  useComputedColorScheme,
  Button,
} from "@mantine/core";

import { FaSun, FaMoon } from "react-icons/fa";

function App() {
  const { setColorScheme } = useMantineColorScheme();
  const computedColorScheme = useComputedColorScheme("light");

  const toggleColorScheme = () => {
    setColorScheme(computedColorScheme === "dark" ? "light" : "dark");
  };
  return (
    <>
      <Button variant="link" onClick={toggleColorScheme}>
        {computedColorScheme === "dark" ? <FaMoon /> : <FaSun />}
      </Button>
      <EventList />
    </>
  );
}

export default App;
