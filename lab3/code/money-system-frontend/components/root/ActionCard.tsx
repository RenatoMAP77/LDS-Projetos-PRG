import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
  } from "@/components/ui/card";
  import Link from "next/link";

interface ActionCardProps {
    title: string;
    description: string;
    icon: React.ReactNode;
    href: string;
  }
  
  export const ActionCard: React.FC<ActionCardProps> = ({
    title,
    description,
    icon,
    href,
  }) => (
    <Card className="transition-all hover:shadow-md hover:-translate-y-1">
      <Link href={href} className="block h-full">
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            {icon}
            <span>{title}</span>
          </CardTitle>
          <CardDescription>{description}</CardDescription>
        </CardHeader>
      </Link>
    </Card>
  );