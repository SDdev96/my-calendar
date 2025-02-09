import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

export default defineConfig({
  plugins: [react()],
  /*added server and port manually*/
  server: {
    port: 3000,
  },
  /*added resolve and aliases manually from 'npm install -D @types/node'
  TO-DO: add this in tsconfig.json file */
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"),
      "@assets": path.resolve(__dirname, "./src/assets"),
      "@components": path.resolve(__dirname, "./src/components"),
      "@hooks": path.resolve(__dirname, "./src/hooks"),
      "@services": path.resolve(__dirname, "./src/services"),
      "@config": path.resolve(__dirname, "./src/config"),
    },
  },
});
