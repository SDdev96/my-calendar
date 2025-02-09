import { EVENTS_SERVICE } from "../config/constants";
import { EventParams } from "../config/types";

const eventsService = async ({ title }: EventParams = {}): Promise<any> => {
  const url = new URL(EVENTS_SERVICE);

  if (title) url.searchParams.append("title", title);

  return fetch(url.toString()).then((response) => {
    if (!response.ok) {
      throw new Error(`Errore HTTP! Status: ${response.status}`);
    }
    return response.json();
  });
};

export default eventsService;
