package Utils;

import java.util.Random;

public enum RandomPassword {
    A1b2c3, B3d4e5, C6e7f8, D9g0h1, E2i3j4, F5k6l7, G8m9n0, H1o2p3, I4q5r6, J7s8t9, K0u1v2, L3w4x5, M6y7z8, N9a0b1, O2c3d4, P5e6f7, Q8g9h0, R1i2j3, S4k5l6, T7m8n9, U0o1p2, V3q4r5, W6s7t8, X9u0v1, Y2w3x4, Z5y6z7, A8z9a0, B0b1c2, C3d4e5, D6f7g8, E9h0i1, F2j3k4, G5l6m7, H8n9o0, I0p1q2, J3r4s5, K6t7u8, L9v0w1, M2x3y4, N5y6z7, O8z9a0, P0b1c2, Q3d4e5, R6f7g8, S9h0i1, T2j3k4, U5l6m7, V8n9o0, W0p1q2, X3r4s5, Y6t7u8, Z9v0w1, A2x3y4, B5y6z7, C8z9a0, D0b1c2, E3d4e5, F6f7g8, G9h0i1, H2j3k4, I5l6m7, J8n9o0, K0p1q2, L3r4s5, M6t7u8, N9v0w1, O2x3y4, P5y6z7, Q8z9a0, R0b1c2, S3d4e5, T6f7g8, U9h0i1, V2j3k4, W5l6m7, X8n9o0, Y0p1q2;

    public static String getRandomPassword() {
        Random random = new Random();
        RandomPassword[] passwords = values();
        return passwords[random.nextInt(passwords.length)].toString();
    }
}
