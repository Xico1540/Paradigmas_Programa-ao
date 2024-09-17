/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CBL;

import Exceptions.EditionAlreadyInListException;
import Exceptions.InvalidIndexException;
import Exceptions.InvalidProjectNameException;
import java.io.IOException;
import java.text.ParseException;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

/* 
* Nome: Lucas Teixeira Fernandes
* Número: 8220297
* Turma: 3
* 
* Nome: Francisco Jose Pinto costa
* Número: 8220805
* Turma: 1
 */
public interface CblManagement {

    /**
     * @details A função devolve a posição fornecida se esta é válida retornando
     * -1 caso esta seja inválida
     * @param index
     * @return retorna uma edição com base no índice fornecido.
     */
    Edition returnEdition(int index);

    /**
     * Adiciona uma edição à lista de edições
     *
     * @param edition
     * @throws EditionAlreadyInListException se a edição já existir na lista
     */
    void addEdition(Edition edition) throws EditionAlreadyInListException;

    /**
     *
     * Remove uma edição da lista de edições com base no índice fornecido.
     *
     *
     * @param index o índice da edição a ser removida
     * @throws InvalidIndexException se o índice for inválido (nulo ou fora dos
     * limites da lista)
     */
    void removeEdition(int index) throws InvalidIndexException;

    /**
     *
     * Define uma edição como ativa com base no índice fornecido, desativando
     * todas as outras edições.
     *
     *
     * @param index o índice da edição a ser definida como ativa
     * @throws InvalidIndexException se o índice for inválido (nulo ou fora dos
     * limites da lista)
     */
    void setActiveEdition(int index) throws InvalidIndexException;

    /**
     *
     * Adiciona um projeto à edição com base no índice fornecido, fornecendo o
     * nome, descrição e tags do projeto.
     *
     *
     * @param editionIndex o índice da edição à qual o projeto será adicionado
     * @param name o nome do projeto
     * @param description a descrição do projeto
     * @param tags as tags associadas ao projeto
     * @throws ParseException se ocorrer um erro durante o parse do modelo JSON
     * @throws IOException se ocorrer um erro ao tentar ler o modelo JSON
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    void addProjectToEdition(int editionIndex, String name, String description, String[] tags) throws ParseException, IOException, InvalidIndexException;

    /**
     *
     * Retorna um array de edições que possuem submissões em falta.
     *
     *
     * @return um array de edições com submissões em falta
     */
    Edition[] getEditionsWithMissingSubmissions();

    /**
     *
     * Retorna um array de projetos com submissões em falta na edição com o
     * índice fornecido.
     *
     *
     * @param index o índice da edição
     * @return um array de projetos com submissões em falta
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    Project[] getProjectsMissingSubmissions(int index) throws InvalidIndexException;

    /**
     *
     * Retorna o número de projetos na edição com o índice fornecido.
     *
     *
     * @param index o índice da edição
     * @return o número de projetos na edição
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    int getNumberOfProjects(int index) throws InvalidIndexException;

    /**
     *
     * Retorna o número de edições.
     *
     *
     * @return o número de edições
     */
    int getNumberOfEditions();

    /**
     *
     * Retorna uma string com o progresso do projeto com o nome fornecido na
     * edição com o índice fornecido.
     *
     *
     * @param index o índice da edição
     * @param projectName o nome do projeto
     * @return uma string com o progresso do projeto
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista) ou se o nome do projeto for inválido
     */
    String getProjectProgressText(int index, String projectName) throws InvalidIndexException;

    /**
     *
     * Retorna uma string com o progresso da edição com o índice fornecido.
     *
     *
     * @param index o índice da edição
     * @return uma string com o progresso da edição
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    String getEditionProgressText(int index) throws InvalidIndexException;

    /**
     *
     * Adiciona uma submissão a um projeto com base no nome do projeto, título
     * da submissão, objeto de submissão e email do participante.
     *
     *
     * @param projectName o nome do projeto
     * @param submissionTitle o título da submissão
     * @param submission o objeto de submissão a ser adicionado
     * @param email o email do participante
     * @throws InvalidIndexException se não houver nenhuma edição ativa ou se o
     * título da tarefa ou email do participante forem inválidos
     * @throws InvalidProjectNameException se o nome do projeto for inválido
     */
    void addSubmissionToProject(String projectName, String submissionTitle, Submission submission, String email) throws InvalidIndexException, InvalidProjectNameException;

    /**
     *
     * @return uma string com a lista de edições
     */
    String listEditions();

    /**
     *
     * @param index de uma edição
     * @return uma string com a lista de projetos de uma edição
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    String listProjectsByEdition(int index) throws InvalidIndexException;

    /**
     *
     * @param index de uma edição
     * @return uma string com a lista de projetos completos de uma dada edição
     * @throws InvalidIndexException se o índice da edição for inválido (nulo ou
     * fora dos limites da lista)
     */
    String listCompletedProjectsByEdition(int index) throws InvalidIndexException;
}
