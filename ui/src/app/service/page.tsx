import { Params } from "@/types";
import { ServiceList } from "@/feature/service-list/container";
import { CreateService } from "@/feature/create-service/container";
import { DataTitle } from "@/components/data/Title";

type ServicePageProps = {
  searchParams: Params;
};

export default function ServicePage({ searchParams }: ServicePageProps) {
  return (
    <main>
      <DataTitle value={"Teenused"}>
        <CreateService />
      </DataTitle>

      <ServiceList params={searchParams} />
    </main>
  );
}
