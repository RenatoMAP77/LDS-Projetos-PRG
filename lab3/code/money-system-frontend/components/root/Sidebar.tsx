

"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import {
    Breadcrumb,
    BreadcrumbItem,
    BreadcrumbLink,
    BreadcrumbList,
    BreadcrumbPage,
    BreadcrumbSeparator,
} from "@/components/ui/breadcrumb";
import { Separator } from "@/components/ui/separator";
import {
    Sidebar,
    SidebarContent,
    SidebarGroup,
    SidebarHeader,
    SidebarInset,
    SidebarMenu,
    SidebarMenuButton,
    SidebarMenuItem,
    SidebarProvider,
    SidebarTrigger,
} from "@/components/ui/sidebar";
import Link from "next/link";
import { data } from "@/lib/utils";

export default function SidebarComponent({ children }: { children: React.ReactNode }) {
    const [breadcrumb, setBreadcrumb] = useState("Início");
    const [filteredNavMain, setFilteredNavMain] = useState(data.navMain);

    useEffect(() => {
        const userType = localStorage.getItem('type');
        let filtered;

        switch (userType) {
            case 'PROFESSOR':
                filtered = data.navMain.filter(item =>
                    item.title === "Professores"
                );
                break;
            case 'ALUNO':
                filtered = data.navMain.filter(item =>
                    item.title === "Alunos"
                );
                break;
            case 'EMPRESA':
                filtered = data.navMain.filter(item =>
                    item.title === "Empresas" || item.title === "Vantagens"
                );
                break;
            default:
                filtered = data.navMain;
        }

        setFilteredNavMain(filtered);
    }, []);

    return (
        <SidebarProvider>
            <Sidebar variant="inset">
                <SidebarHeader>
                    <SidebarMenu>
                        <SidebarMenuItem>
                            <SidebarMenuButton size="lg" asChild>
                                <Link href="">
                                    <div className="flex size-24 items-center justify-center rounded-lg ">
                                        <Image
                                            src="/logo.png"
                                            width={200}
                                            height={200}
                                            alt="Logo"
                                        />
                                    </div>
                                    <div className="grid flex-1 text-left text-sm leading-tight">
                                        <span className="truncate font-semibold">Moeda Estudantil</span>
                                        <span className="truncate text-xs">Versão 2.0</span>
                                    </div>
                                </Link>
                            </SidebarMenuButton>
                        </SidebarMenuItem>
                    </SidebarMenu>
                </SidebarHeader>
                <SidebarContent>
                    <SidebarGroup>
                        <SidebarMenu>
                            {filteredNavMain.map((item) => (
                                <SidebarMenuItem key={item.title}>

                                    {item.items && (
                                        <SidebarMenu>
                                            {item.items.map((subItem) => (
                                                <SidebarMenuItem key={subItem.title}>
                                                    <SidebarMenuButton asChild>
                                                        <Link
                                                            href={subItem.url}
                                                            className="pl-8"
                                                            onClick={() => setBreadcrumb(subItem.breadcrumb)}
                                                        >
                                                            {subItem.title}
                                                        </Link>
                                                    </SidebarMenuButton>
                                                </SidebarMenuItem>
                                            ))}
                                        </SidebarMenu>
                                    )}
                                </SidebarMenuItem>
                            ))}
                        </SidebarMenu>
                    </SidebarGroup>
                </SidebarContent>
            </Sidebar>
            <SidebarInset>
                <header className="flex h-16 shrink-0 items-center gap-2">
                    <div className="flex items-center gap-2 px-4">
                        <SidebarTrigger className="-ml-1" />
                        <Separator orientation="vertical" className="mr-2 h-4" />
                        <Breadcrumb>
                            <BreadcrumbList>
                                <BreadcrumbItem>
                                    <BreadcrumbLink href="">Início</BreadcrumbLink>
                                </BreadcrumbItem>
                                <BreadcrumbSeparator />
                                {breadcrumb !== "Início" && (
                                    <BreadcrumbItem>
                                        <BreadcrumbPage>{breadcrumb}</BreadcrumbPage>
                                    </BreadcrumbItem>
                                )}
                            </BreadcrumbList>
                        </Breadcrumb>
                    </div>
                </header>
                <div className="flex flex-1 flex-col gap-4 p-4 pt-0">
                    <div className="min-h-[100vh] flex-1 rounded-xl bg-muted/50 md:min-h-min">
                        {children}
                    </div>
                </div>
            </SidebarInset>
        </SidebarProvider>
    );
}