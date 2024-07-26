package Utils;

import java.util.Random;

public enum RandomFirstName {
    Jacob, Emma, Olivia, Noah, Ava, William, Sophia, James, Isabella, Benjamin, Mia, Lucas, Amelia, Mason, Harper, Ethan, Evelyn, Alexander, Abigail, Henry, Ella, Scarlett, Michael, Grace, Elijah, Lily, Logan, Aria, Jackson, Chloe, Aiden, Charlotte, Daniel, Zoey, Matthew, Stella, Samuel, Hazel, David, Nora, Joseph, Mila, Carter, Aurora, Owen, Riley, John, Emilia, Luke, Penelope, Gabriel, Layla, Anthony, Lillian, Isaac, Addison, Grayson, Eleanor, Jayden, Hannah, Julian, Natalie, Leo, Zoe, Sebastian, Leah, Jack, Savannah, Hunter, Christian, Bella, Ezra, Lincoln, Maya, Cameron, Elias, Audrey, Joshua, Lucy, Nathan, Brooklyn, Charles, Caleb, Claire, Ryan, Anna, Jaxon, Elise, Thomas, Skylar, Aaron, Violet, Harrison, Levi;

    public static String getRandomName() {
        Random random = new Random();
        RandomFirstName[] names = values();
        return names[random.nextInt(names.length)].toString();
    }
}
