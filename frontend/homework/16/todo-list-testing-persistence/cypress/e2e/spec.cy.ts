describe("Todo Application", () => {
  it("allows a user to add a new todo item and then delete it", () => {
    // Visit the home page
    cy.visit("http://localhost:3000");

    // Type a new todo item
    cy.get('[data-testid="add-item-input"]').type("Learn Cypress");

    // Click the add button
    cy.get('[data-testid="add-item-submit"]').click();

    
    cy.contains("Learn Cypress").should("exist");

  
    cy.contains("Learn Cypress")
      .parent()
      .find('[data-testid="delete-todo"]')
      .click();

    cy.contains("Learn Cypress").should("not.exist");
  });
});
 