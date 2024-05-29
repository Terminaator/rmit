import React from "react";
import { Service } from "@/types";
import { Suspense } from "react";
import { ServiceListProps } from "@/feature/service-list/type";
import { getServicesAction } from "@/feature/service-list/action";
import { DataSearch } from "@/components/data/Search";
import { DataTable } from "@/components/data/Table";

const table = {
  get: getServicesAction,
  columns: [
    {
      header: "Kood",
      value: (service: Service) => service.code,
    },
    {
      header: "Rakenduse kood",
      value: (service: Service) => service.appCode,
    },
    {
      header: "Nimi",
      value: (service: Service) => service.name,
    },
    {
      header: "Tüüp",
      value: (service: Service) => service.type,
    },
    {
      header: "Alamtüüp",
      value: (service: Service) => service.subType,
    },
    {
      header: "Muudetud",
      value: (service: Service) => service.modified,
    },
  ],
  caption: "Teadmata kadunud teenused...",
  searchParamName: "name",
};

export const ServiceList = ({ params }: ServiceListProps) => {
  return (
    <div className="flex flex-1 flex-col gap-4 p-4 lg:gap-6 lg:p-6">
      <Suspense>
        <DataSearch placeholder={"Otsi teenust rakenduse nime järgi..."} />
      </Suspense>

      <div className="flex items-center">
        <DataTable params={params} {...table} />
      </div>
    </div>
  );
};
