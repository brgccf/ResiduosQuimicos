Feature: Operar com laboratorios disponíveis no sistema.
  As an usuário do tipo Facilitador logado no sistema
  I want to realizar operações com os laboratórios cadastrados
  so that i possa realizar cadastramento de resíduos no sistema

  Scenario: Solicitação de acesso a laboratórios por usuário do tipo Facilitador
    Given "Pedro" não possui associação a nenhum laboratório cadastrado
    And os laboratórios "A" e "B" estão disponíveis para associação
    When Eu solicito a associação de "Pedro" ao Laboratório "A"
    Then o laboratório "A" não pode receber mais solicitações

  Scenario: Aprovação de acesso a laboratório para um usuário do tipo Facilitador
    Given "Fatima" é um usuário do tipo administrador do sistema
    And Existe uma solicitação de acesso ao laboratório "A" feita pelo usuário do tipo Facilitador "Pedro"
    When "Fatima" realiza a operação de concessão de acesso ao laboratório "A" para "Pedro"
    Then "Pedro" passa a ficar associado ao laboratório "A"
    And "Pedro" não pode mais solicitar acesso a laboratórios

  Scenario: Tentativa de solicitação de acesso a laboratório com usuário associado
    Given "Pedro" é um usuário faciitador associado ao laboratório "A"
    And o laboratório "B" está disponível para associação
    When eu tento solicitar associação de "Pedro" ao laboratório "B"
    Then eu posso ver uma mensagem de erro indicando que "Pedro" já está associado laboratorio "A"

  Scenario: Confirmação de solicitação de acesso a laboratório para usuário Facilitador
    Given "Marcos" é um usuário do tipo facilitador sem associações a laboratórios
    And o laboratório "B" está disponível para associação
    When eu tento solicito associação de "Marcos" ao laboratório "B"
    Then eu posso ver uma mensagem de confirmação indicando a solicitação de "Marcos" para acessar o laboratório "B"