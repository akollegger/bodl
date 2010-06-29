package bodl.sandbox.helloscala.internal

import bodl.sandbox.helloscala.ExampleService

/**
 * Internal implementation of our example OSGi service
 */
class ExampleServiceImpl
    extends ExampleService
{
    // implementation methods go here...

    def scramble(text:String):String = {
        val textChars = text.toCharArray();
        val charList = textChars.map( c => new Character(c)).toList
        println("charList: " + charList)
        /*
        for( c <- textChars )
        {
            charList :: new Character( c );
        }

        java.util.Collections.shuffle( charList );

        val mixedChars = charList.map[Character]( c => c.charValue
        char[] mixedChars = new char[text.length()];
        for( int i = 0; i < mixedChars.length; i++ )
        {
            mixedChars[i] = ( (Character) charList.get( i ) ).charValue();
        }

        return new String( mixedChars );
        */

        val rnd = new java.util.Random()
        val shuffled = charList.sort((a,b) => rnd.nextBoolean())

        println("shuffled: " + shuffled)

        shuffled.mkString
    }

}

