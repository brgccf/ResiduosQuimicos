Feature: Operar com laboratorios disponíveis no sistema.
  As an usuário do tipo Facilitador logado no sistema
  I want to realizar operações com os laboratórios cadastrados
  so that i possa realizar cadastramento de resíduos no sistema

  Scenario: Solicitação de acesso a laboratórios por usuário do tipo Facilitador
    Given "Pedro" não possui associação a nenhum laboratório cadastrado
    And a lista de solicitações de associações ao laboratório "A" por usuários do tipo Facilitador está vazia
    And os laboratórios "A" e "B" estão disponíveis para associação
    When Eu solicito a associação de "Pedro" ao Laboratório "A"
    Then a lista de solicitações de associações é atualizada com uma solicitação de "Pedro" para acessar "A"

  Scenario: Aprovação de acesso a laboratório para um usuário do tipo Facilitador
    Given "Fátima" é um usuário do tipo administrador do sistema
    And Existe uma solicitação de acesso ao laboratório "A" feita pelo usuário do tipo Facilitador "Pedro"
    When "Fátima" realiza a operação de concessão de acesso ao laboratório "A" para "Pedro"
    Then "Pedro" passa a ficar associado ao laboratório "A"
    And "Pedro" não pode mais solicitar acesso a laboratórios