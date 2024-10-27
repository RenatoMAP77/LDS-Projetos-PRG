"use client"
import React, { createContext, useContext } from 'react';
import { tipoUsuario } from '@/lib/types';
import { createEntidade, updateEntidade, readAllEntidades, readEntidadeById ,deleteEntidade} from '@/services/crudService';
import { useToast } from '@/hooks/use-toast';


interface EntidadeContextProps {
  adicionarEntidade: (entidade: any, tipo: tipoUsuario) => Promise<void>;
  editarEntidade: (id: string, entidade: any, tipo: tipoUsuario) => Promise<void>;
    deletarEntidade: (id: string, tipo: tipoUsuario) => Promise<void>;
    lerEntidades: (tipo: tipoUsuario) => Promise<any>;
    lerEntidadePorId: (id: string, tipo: tipoUsuario) => Promise<any>;
}

const EntidadeContext = createContext<EntidadeContextProps | undefined>(undefined);

export const EntidadeProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { toast } = useToast();
  

  const lerEntidades = async (entidade:any) => {
    try {
      return await readAllEntidades(entidade);
    } catch (error) {
      toast({
        title: "Erro ao buscar entidades",
        description: "Tente novamente.",
      });
    }
  }

    const lerEntidadePorId = async (id: string, entidade:any) => {
        try {
        return await readEntidadeById(id, entidade);
        } catch (error) {
        toast({
            title: "Erro ao buscar entidade",
            description: "Tente novamente.",
        });
        }
    };

    const deletarEntidade = async (id: string, entidade:any) => {
        try {
        await deleteEntidade(id, entidade);
        toast({
            title: "Entidade deletada com sucesso!",
            description: "A entidade foi deletada.",
        });
        } catch (error) {
        toast({
            title: "Erro ao deletar entidade",
            description: "Tente novamente.",
        });
        }
    }

  const adicionarEntidade = async (entidade: any, tipo: tipoUsuario) => {
    try {
      await createEntidade(entidade, tipo);
      toast({
        title: `${tipo} criado com sucesso!`,
        description: "A entidade foi criada.",
      });
    } catch (error) {
      toast({
        title: "Erro ao criar entidade",
        description: "Tente novamente.",
      });
    }
  };

  const editarEntidade = async (id: string, entidade: any, tipo: tipoUsuario) => {
    try {
      await updateEntidade({ ...entidade, id }, tipo);
      toast({
        title: `${tipo} atualizado com sucesso!`,
        description: "A entidade foi atualizada.",
      });
    } catch (error) {
      toast({
        title: "Erro ao atualizar entidade",
        description: "Tente novamente.",
      });
    }
  };

  return (
    <EntidadeContext.Provider value={{ adicionarEntidade, editarEntidade, deletarEntidade, lerEntidadePorId, lerEntidades }}>
      {children}
    </EntidadeContext.Provider>
  );
};

export const useEntidade = () => {
  const context = useContext(EntidadeContext);
  if (!context) {
    throw new Error('useEntidade must be used within a EntidadeProvider');
  }
  return context;
};
