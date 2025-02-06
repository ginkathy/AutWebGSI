@ProductStore
Feature: Product - Store

  @loginStore
  Scenario Outline: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "<usuario>" y clave "<password>"
    When navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    And agrego <cantidad> unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

    Examples:
      | usuario              | password   | categoria | subcategoria | cantidad |
      | gingersc@hotmail.com | B&G082015/ | Clothes   | Men          | 2        |
      | gingersc@hotmail.com | 1234567890 | Clothes   | Men          | 1        |
      | gingersc@hotmail.com | B&G082015/ | Car       | New          | 2        |

