import React, { useEffect } from "react";
import { Form } from "@/components/ui/form";
import { Button } from "@/components/ui/button";
import { useFormState } from "react-dom";
import { useToast } from "@/components/ui/use-toast";
import { UseFormReturn } from "react-hook-form";
import { DataFormField, DataFormFieldType } from "@/components/data/FormField";
import { DataFormState } from "@/components/data/Form/type";
import { UNSET_DATA_FORM_STATE } from "@/components/data/Form/constant";

type DataFormProps = {
  // eslint-disable-next-line no-unused-vars
  submit: (state: DataFormState, form: FormData) => Promise<DataFormState>;
  form: UseFormReturn<any, any, any>;
  fields: DataFormFieldType[];
};

export const DataForm = ({ submit, form, fields }: DataFormProps) => {
  const [state, setState] = useFormState(submit, UNSET_DATA_FORM_STATE);

  const { toast } = useToast();

  useEffect(() => {
    if (state.message) {
      toast({
        variant: state.status === "ERROR" ? "destructive" : "default",
        description: state.message,
      });
    }
  }, [state, toast]);

  return (
    <Form {...form}>
      <form
        onSubmit={(evt) => {
          evt.preventDefault();

          form.handleSubmit((data) => {
            const form = new FormData();

            Object.keys(data).forEach((key) => {
              const value = data[key];

              if (value !== undefined && value !== null) {
                form.append(key, value);
              }
            });

            setState(form);
          })(evt);
        }}
      >
        {fields.map((field) => (
          <DataFormField
            key={field.name}
            control={form.control}
            name={field.name}
            label={field.label}
            description={field.description}
            options={field.options}
            type={field.type}
          />
        ))}

        <Button type="submit" className="ml-auto flex items-center">
          Sisesta
        </Button>
      </form>
    </Form>
  );
};
