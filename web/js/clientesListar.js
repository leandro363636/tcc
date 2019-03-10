/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $(".ion-trash-a").on("click", function () {
        return confirm("Você tem certeza que deseja remover esse cliente?");
    });
})

