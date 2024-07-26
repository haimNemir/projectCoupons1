package Utils;

import java.util.Random;

public enum RandomLastName {
    Smith, Johnson, Williams, Brown, Jones, Garcia, Miller, Davis, Rodriguez, Martinez, Hernandez, Lopez, Gonzalez, Wilson, Anderson, Thomas, Taylor, Moore, Jackson, Martin, Lee, Perez, Thompson, White, Harris, Sanchez, Clark, Ramirez, Lewis, Robinson, Walker, Young, Allen, King, Wright, Scott, Torres, Nguyen, Hill, Adams, Baker, Nelson, Carter, Mitchell, Roberts, Evans, Turner, Phillips, Campbell, Parker, Morris, Murphy, Rivera, Cook, Rogers, Morgan, Bell, Bailey, Cooper, Richardson, Cox, Howard, Ward, Flores, James, Price, Bennett, Wood, Barnes, Ross, Henderson, Coleman, Jenkins, Perry, Powell, Long, Patterson, Hughes, Washington, Butler, Simmons, Foster, Bryant, Alexander, Russell, Griffin, Diaz, Hayes, Myers, Weaver, Olson, Lane, Chapman, Newman, Burton, Gallagher, Bowman, Silva;

    public static String getRandomName() {
        Random random = new Random();
        RandomLastName[] names = values();
        return names[random.nextInt(names.length)].toString();
    }
}
