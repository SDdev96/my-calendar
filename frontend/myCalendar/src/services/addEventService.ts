import { EVENTS_SERVICE } from "../config/constants";
import { EventInterface } from "../config/types";

const addEventService = async (event: EventInterface) => {
  await fetch(EVENTS_SERVICE, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title: event.title,
      description: event.description,
      start: event.start,
      end: event.end,
    }),
  }).then(async (response) => {
    if (!response.ok) {
      const errorData = await response.json();
      throw errorData;
    }
    return response.text();
  });
};

export default addEventService;
