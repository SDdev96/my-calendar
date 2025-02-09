import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import "@mantine/core/styles.css";
import App from "./App.tsx";

import { MantineProvider, createTheme } from "@mantine/core";

const theme = createTheme({
  //Mantine theme override here
});

const root = createRoot(document.getElementById("root")!);

root.render(
  <MantineProvider theme={theme} defaultColorScheme="dark">
    <StrictMode>
      <App />
    </StrictMode>
  </MantineProvider>
);
