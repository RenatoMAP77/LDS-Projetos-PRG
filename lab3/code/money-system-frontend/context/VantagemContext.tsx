"use client"
import { createContext, useContext } from 'react';
import { createVantagem, readAllVantagens } from '@/services/vantagemService';
import { useToast } from '@/hooks/use-toast';
import { VantagemCreate, VantagemRead } from '@/lib/types';

interface VantagemContextProps {
  adicionarVantagem: (vantagem: VantagemCreate) => Promise<void>;
  lerVantagens: () => Promise<VantagemRead[] | undefined>;
}

const VantagemContext = createContext<VantagemContextProps | undefined>(undefined);

export const VantagemProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { toast } = useToast();

  const lerVantagens = async (): Promise<VantagemRead[] | undefined> => {
    try {
      return await readAllVantagens();
    } catch (error) {
      toast({
        title: "Erro ao buscar entidades",
        description: "Tente novamente.",
      });
    }
  };

  const adicionarVantagem = async (dadosVantagem: VantagemCreate) => {
    try {
    
      await createVantagem(dadosVantagem);
      toast({
        title: "Vantagem criada com sucesso",
        description: "A entidade foi criada.",
      });
    } catch (error) {
      toast({
        title: "Erro ao criar entidade",
        description: "Tente novamente.",
      });
    }
  };

  return (
    <VantagemContext.Provider value={{ lerVantagens, adicionarVantagem }}>
      {children}
    </VantagemContext.Provider>
  );
};

export const useVantagem = () => {
  const context = useContext(VantagemContext);
  if (!context) {
    throw new Error('useEntidade must be used within a EntidadeProvider');
  }
  return context;
};
