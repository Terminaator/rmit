import {
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import React from "react";
import { Control } from "react-hook-form";
import { Input } from "@/components/ui/input";

export type DataFormFieldType = {
  name: string;
  label: string;
  description: string;
  type: "INPUT" | "SELECT";
  options?: readonly string[];
};

type DataFormFieldProps = {
  control: Control;
} & DataFormFieldType;

export const DataFormField = ({
  control,
  name,
  label,
  description,
  type,
  options,
}: DataFormFieldProps) => {
  return (
    <FormField
      control={control}
      name={name}
      render={({ field }) => (
        <FormItem>
          <FormLabel>{label}</FormLabel>
          {type === "INPUT" && (
            <FormControl>
              <Input {...field} />
            </FormControl>
          )}

          {type === "SELECT" && (
            <Select
              onValueChange={field.onChange}
              value={field.value}
              defaultValue={field.value}
            >
              <FormControl>
                <SelectTrigger>
                  <SelectValue placeholder="Vali väärtus!" />
                </SelectTrigger>
              </FormControl>
              <SelectContent>
                {options &&
                  options.map((option) => (
                    <SelectItem key={option} value={option.toString()}>
                      {option}
                    </SelectItem>
                  ))}
              </SelectContent>
            </Select>
          )}
          <FormDescription>{description}</FormDescription>
          <FormMessage />
        </FormItem>
      )}
    />
  );
};
