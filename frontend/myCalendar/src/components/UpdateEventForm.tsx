import { useForm } from "@mantine/form";
import { TextInput, Textarea, Button, Stack, Group } from "@mantine/core";
import { EventInterface } from "../config/types";

import useUpdateEvent from "../hooks/useUpdateEvent";

interface EventFormProps {
  event: EventInterface;
  isNew: boolean;
  onSubmit: (values: EventInterface) => void;
  onClose: () => void;
  handleRefreshUI: () => void;
}

const EventForm = ({ event, isNew, onClose, onSubmit, handleRefreshUI }: EventFormProps) => {
  const form = useForm({
    mode: "uncontrolled",
    initialValues: {
      id: event.id,
      title: event.title,
      description: event.description,
      start: new Date(event.start)
        .toLocaleString("sv-SE")
        .replace(" ", "T")
        .slice(0, 16),
      end: new Date(event.end)
        .toLocaleString("sv-SE")
        .replace(" ", "T")
        .slice(0, 16),
    },
    validate: {
      title: (value) => (value?.trim() !== "" ? null : "Title needed"),
    },
  });

  const { newEvent, updateEvent, deleteEvent, addEvent } = useUpdateEvent(
    onSubmit,
    onClose,
    handleRefreshUI,
  );

  return (
    <form
      onSubmit={form.onSubmit((values) => {
        isNew ? addEvent(values) : updateEvent(values);
      })}
    >
      <Stack>
        <TextInput
          label="Titolo"
          key={form.key("title")}
          {...form.getInputProps("title")}
        />
        <Textarea
          label="Descrizione"
          key={form.key("description")}
          {...form.getInputProps("description")}
        />
        <TextInput
          label="Data Inizio"
          type="datetime-local"
          key={form.key("start")}
          {...form.getInputProps("start")}
        />
        <TextInput
          label="Data Fine"
          type="datetime-local"
          key={form.key("end")}
          {...form.getInputProps("end")}
        />

        <Group>
          <Button type="submit" disabled={newEvent.isPending}>
            {isNew
              ? "Aggiungi evento"
              : newEvent.isPending
              ? "Aggiornamento..."
              : "Salva Modifiche"}
          </Button>
          <Button
            onClick={() => {
              deleteEvent(event.id);
            }}
            disabled={newEvent.isPending}
            style={{ display: isNew ? "none" : "block" }}
          >
            {newEvent.isPending ? "Eliminazione..." : "Elimina evento"}
          </Button>
        </Group>
      </Stack>
    </form>
  );
};

export default EventForm;

