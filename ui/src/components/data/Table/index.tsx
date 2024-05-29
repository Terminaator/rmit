"use server";

import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import React from "react";
import { Code, Params } from "@/types";
import { DataAlert } from "@/components/data/Alert";
import { GetResponse } from "@/actions/types";

type TableProps<T extends Code> = {
  // eslint-disable-next-line no-unused-vars
  get: (search: string) => Promise<GetResponse<T[]>>;
  // eslint-disable-next-line no-unused-vars
  columns: { header: string; value: (row: T) => string }[];
  caption: string;
  params: Params;
  searchParamName: string;
};

const getValueFromParams = (params: Params, field: string): string => {
  let value = params[field];

  if (Array.isArray(value)) {
    value = value[0];
  }

  return value || "";
};

export const DataTable = async <T extends Code>({
  get,
  columns,
  caption,
  params,
  searchParamName,
}: TableProps<T>) => {
  const response = await get(getValueFromParams(params, searchParamName));

  return (
    <div className="w-full">
      {!response.ok && <DataAlert message={response.error!} />}

      <Table>
        {!response.data?.length && <TableCaption>{caption}</TableCaption>}
        <TableHeader>
          <TableRow>
            {columns.map((column) => (
              <TableHead key={column.header}>{column.header}</TableHead>
            ))}
          </TableRow>
        </TableHeader>
        <TableBody>
          {response.data &&
            response.data.map((row) => (
              <TableRow key={row.code}>
                {columns.map((column) => (
                  <TableCell key={`${row.code}-${column.header}`}>
                    {column.value(row)}
                  </TableCell>
                ))}
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};
