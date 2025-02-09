import { Fragment, useState } from "react";

import useGetEvents from "../hooks/useGetEvents";

import Event from "./Event.tsx";
import UpdateEventForm from "./UpdateEventForm.tsx";

import { EventInterface } from "../config/types.ts";

import "./eventList.css";

import {
  SimpleGrid,
  Container,
  Modal,
  TextInput,
  Button,
  Group,
  Text,
  Flex,
} from "@mantine/core";

const EventList = () => {
  const [title, setTitle] = useState<string>("");
  const [refreshTrigger, setRefreshTrigger] = useState<boolean>(true);

  const {
    data: dataEvents,
    error: errorEvents,
    isPending: isPendingEvents,
  } = useGetEvents({ title }, refreshTrigger);

  const handleRefreshUI = () => {
    setRefreshTrigger((prev) => !prev);
  };

  const [opened, setOpened] = useState(false);
  const [selectedEvent, setSelectedEvent] = useState<EventInterface | null>(
    null
  );

  const openModal = (event: EventInterface | null) => {
    setSelectedEvent(event);
    setOpened(true);
  };

  const handleSubmit = () => {
    setOpened(false);
  };

  return (
    <Fragment>
      <Group align="end" justify="center">
        {dataEvents.length !== 0 && (
          <Button onClick={() => openModal(null)}>+</Button>
        )}
        <TextInput
          placeholder="Insert title.."
          description="Press Enter to search"
          onKeyDown={(event) => {
            if (event.key === "Enter") {
              setTitle(event.currentTarget.value);
            }
          }}
        />
      </Group>

      <Flex
        justify="center"
        align="center"
        direction="row"
        wrap="wrap"
        mih={200}
      >
        {isPendingEvents && (
          <Text span style={{ color: "gray", fontSize: "18px" }}>
            Caricamento..
          </Text>
        )}
        {errorEvents && (
          <Text span style={{ color: "red", fontSize: "18px" }}>
            Errore: {errorEvents}
          </Text>
        )}
        {!isPendingEvents && !errorEvents && dataEvents.length === 0 ? (
          <Flex justify="center" align="center" direction="column">
            <Text span style={{ color: "gray", fontSize: "18px" }}>
              Lista eventi vuota
            </Text>
            <Button onClick={() => openModal(null)} size="xs" color="gray">
              <Text
                span
                style={{ color: "gray" }}
                size="sm"
                fw={600}
                variant="gradient"
                gradient={{ from: "orange", to: "green", deg: 90 }}
              >
                Aggiungi un evento
              </Text>
            </Button>
          </Flex>
        ) : (
          <SimpleGrid className="event-list">
            {dataEvents.map((event: EventInterface) => (
              <Event
                key={event.id}
                {...event}
                onClick={() => openModal(event)}
              />
            ))}
          </SimpleGrid>
        )}
      </Flex>
      <Modal
        opened={opened}
        onClose={() => setOpened(false)}
        title={selectedEvent ? "Modifica Evento" : "Aggiungi Evento"}
        centered
      >
        {
          <UpdateEventForm
            event={
              selectedEvent ?? {
                id: null,
                title: "",
                description: "",
                start: "",
                end: "",
              }
            }
            isNew={selectedEvent ? false : true}
            onSubmit={handleSubmit}
            onClose={() => setOpened(false)}
            handleRefreshUI={handleRefreshUI}
          />
        }
      </Modal>
    </Fragment>
  );
};

export default EventList;
