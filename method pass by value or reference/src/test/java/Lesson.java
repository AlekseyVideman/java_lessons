import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*

В Java, при передаче аргументов методу, аргументы передаются по значению — не сами аргументы, а их копии — будь то сcылка на объект (non-primitive) или примитив (primitive).
Перед вызовом метода, в стеке потока создаётся новй фрейм, а в нём выделяется место под копии переданных аргументов.

*/
public class Lesson {

    /*

    Пример передачи ссылки на объект.
    В стеке создастся новая переменная, затем в неё запишется КОПИЯ ссылки на объект obj. Но эта сcылка по-прежнему ссылается на объект в heap, так что я смогу с ним взаимодействовать.

    */
    @Test
    void givenObjectReference_stateChanged_mirroredOnOutsideObject() {
        // arrange
        var obj = new ValueObject();

        // act
        changeState(obj, 22);

        // assert
        assertEquals(22, obj.state);
    }

    private void changeState(ValueObject object, int state) {
        object.state = state;
    }


    /*

    Но если в новую переменную из стека записать новую ссылку, то изменить объект obj не получится.
    Вместо этого я взаимодействую с новым объектом.

    */
    @Test
    void givenObjectReference_stateChanged_notMirroredOnOutsideObject() {
        // arrange
        var obj = new ValueObject();

        // act
        reassignReferenceAndChangeStateOfPassedObject(obj, 22);

        // assert
        assertNotEquals(22, obj.state);
    }

    private void reassignReferenceAndChangeStateOfPassedObject(ValueObject object, int state) {
        object = new ValueObject();
        object.state = state;
    }



    /*

     Пример передачи примитива.
     Продемонстрировать факт копирования на примитиве можно только, если изменить аргумент внутри метода, а снаружи проверить, что оригинальный аргумент не изменился.

    */
    @Test
    void givenPrimivite_changePrimite_originalArgumentDoesNotChanged() {
        // arrange
        int originalPrimitive = 2025;

        // act
        reassignPrimitive(originalPrimitive);

        // assert
        assertEquals(2025, originalPrimitive);
    }

    private void reassignPrimitive(int originalPrimite) {
        originalPrimite = 2028;
    }

}
