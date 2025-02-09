import { useState } from "react";
import updateEventService from "../services/updateEventService";
import deleteEventService from "../services/deleteEventService";
import addEventService from "../services/addEventService";
import { EventInterface } from "../config/types";

const useUpdateEvent = (
  onSubmit: (event: EventInterface) => void,
  onClose: () => void,
  handleRefreshUI: () => void,
) => {
  const [newEvent, setNewEvent] = useState({
    data: {} as EventInterface | null,
    error: "",
    isPending: false,
  });

  const addEvent = async (event: EventInterface) => {
    setNewEvent((prevState) => ({ ...prevState, isPending: true }));

    setTimeout(() => {
      addEventService(event)
        .then(() => {
          console.log("Evento aggiunto:", event);
          setNewEvent((prevState) => ({ ...prevState, data: event }));
          handleRefreshUI();
          onSubmit(event);
        })
        .catch((error: any) => {
          setNewEvent((prevState) => ({ ...prevState, error: error }));
          console.error(error);
        })
        .finally(() => {
          setNewEvent((prevState) => ({ ...prevState, isPending: false }));
        });
      onClose();
    }, 1000);
  };

  const updateEvent = async (event: EventInterface) => {
    setNewEvent((prevState) => ({ ...prevState, isPending: true }));

    setTimeout(() => {
      updateEventService(event)
        .then(() => {
          console.log("Evento aggiornato:", event);
          setNewEvent((prevState) => ({ ...prevState, data: event }));
          handleRefreshUI();
          onSubmit(event);
        })
        .catch((error: any) => {
          setNewEvent((prevState) => ({ ...prevState, error: error }));
          console.error(error);
        })
        .finally(() => {
          setNewEvent((prevState) => ({ ...prevState, isPending: false }));
        });
      onClose();
    }, 1000);
  };

  const deleteEvent = async (id: any) => {
    setNewEvent((prevState) => ({ ...prevState, isPending: true }));

    setTimeout(() => {
      deleteEventService(id)
        .then(() => {
          console.log(`Evento con id ${id} eliminato`);
          setNewEvent((prevState) => ({ ...prevState, data: null }));
          handleRefreshUI();
        })
        .catch((error: any) => {
          setNewEvent((prevState) => ({ ...prevState, error: error }));
          console.error(error);
        })
        .finally(() => {
          setNewEvent((prevState) => ({ ...prevState, isPending: false }));
        });
      onClose();
    }, 1000);
  };

  return { newEvent, updateEvent, deleteEvent, addEvent };
};

export default useUpdateEvent;
