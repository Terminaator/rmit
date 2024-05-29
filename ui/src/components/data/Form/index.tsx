import React, { useEffect, useRef } from "react";
import { Form } from "@/components/ui/form";
import { Button } from "@/components/ui/button";
import { useFormState } from "react-dom";
import { useToast } from "@/components/ui/use-toast";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { createSchema } from "@/lib/utils";
import { DataFormField } from "@/components/data/FormField";
import { DataFormProps } from "@/components/data/Form/type";
import { UNSET_CREATE_FORM_STATE } from "@/components/data/Form/constant";

const useDataForm = (fields: { name: any; type: any; defaultValue: any }[]) => {
  return useForm({
    resolver: zodResolver(createSchema(fields)),
    defaultValues: Object.fromEntries(
      fields.map(({ name, defaultValue }) => [name, defaultValue]),
    ),
  });
};

export const DataForm = ({ action, close, fields }: DataFormProps) => {
  const [state, setState] = useFormState(action, UNSET_CREATE_FORM_STATE);

  const { toast } = useToast();
  const form = useDataForm(fields);

  const ref = useRef<HTMLFormElement>(null);

  useEffect(() => {
    if (state.message) {
      toast({
        variant: state.status === "ERROR" ? "destructive" : "default",
        description: state.message,
      });
    }

    if (state.status === "SUCCESS") {
      return close();
    }
  }, [state, close, toast]);

  return (
    <Form {...form}>
      <form
        ref={ref}
        onSubmit={(evt) => {
          evt.preventDefault();

          form.handleSubmit(() => setState(new FormData(ref.current!)))(evt);
        }}
      >
        {fields.map((field) => (
          <DataFormField
            key={field.name}
            control={form.control}
            name={field.name}
            label={field.label}
            description={field.description}
          />
        ))}

        <Button type="submit" className="ml-auto flex items-center">
          Sisesta
        </Button>
      </form>
    </Form>
  );
};
