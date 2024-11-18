import SidebarComponent from "@/components/root/Sidebar";
import { Toaster } from "@/components/ui/toaster";
import { EntidadeProvider } from "@/context/EntidadeContext";
import { VantagemProvider } from "@/context/VantagemContext";



export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <EntidadeProvider>
            <VantagemProvider>
                <SidebarComponent children={children} />
                <Toaster />
            </VantagemProvider>
        </EntidadeProvider>

    );
}
