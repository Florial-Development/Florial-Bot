package databases;

import databases.models.Story;

import java.sql.*;

public class StoryDatabase {

    private static Connection connection;

    private static final String DATABASE = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";


    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

        System.out.println("Connected to Database.");

        createStoryDatabaseIfNotExists();

        addStory(2,
                "Chapter One",
                "Odin & Freyja are the gods of the universe. In unison, they lead the heavens. Valhalla and Fólkvangr respectfully. They rule the kingdoms of the human's afterlives. Nobody knows where these mystical creatures came from, other than that they have existed since time itself has. They watch over the humans of the physical realm--- the Vikings. All of spiritkind resembles the beautiful fauna created by the Gods. Each human has an animal that resonates with their soul, and they will fill that form in their heavenly ascension. Fauna is such a highly respected part of society, the gods themselves have taken the forms of their own creations. Odin crafted the law, the reason, why, how, and earthly parts of the planet. Freyja created the flora and the fauna, birthing life from nothing.",
                "Three mystical figures named The Valkyries lead the dead to their respectful realm, either in Valhalla or in Fólkvangr, to which the Valkyries, in coordination with the gods, determine the fate of each soul. The Valkyrians themselves are demigods. However, they do not need to worry about the physical assets of themselves while in the spirit realm.",
                "Valhalla is a kingdom where those who died in battle will be resurrected. Across from it is the meadows of Fólkvangr, for those who died a most peaceful death, or, for those who died in combat and had a most peaceful heart. At anytime, the souls from either side may cross to visit one another.",
                "https://media.discordapp.net/attachments/564923688621834251/1177159033988325406/image.png?ex=65717dc8&is=655f08c8&hm=12ca2cd24871bb2853cc06ce1450923435fe86c9b5ec2efe6b8dcf1d04db2135&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177158888701837362/image.png?ex=65717da5&is=655f08a5&hm=6475b17eb6a5978f329f075b797048f940a2742962851a982be57c86e243c01a&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177158947640193084/image.png?ex=65717db3&is=655f08b3&hm=4954d83aa3cf57bfd25420467110209e8acab4aa84259bafe850849afe4acaad&=&format=webp&quality=lossless");
        addStory(3,
                "Chapter Two",
                "As Valkyries, Snø, Brann, as we know them, though these were not originally their names, were in charge of admitting souls into the realms in which they met the requirements for. Bad souls went to Helheim, and good souls either went to Valhalla if dying in battle, or Fólkvangr if dying a rather peaceful death or having a peaceful heart in battle. Whilst, the third Valkyrie, Tulip, as we know her now, assisted with these duties, she had a special task by the gods to keep watch on the physical realm in addition to her other duties in the spirit realm.",
                "After centuries of the Valkyrie's and God's coexistent, Odin foretells a prophecy with the help of Freyja, foreseeing the impending doom of Ragnarok. A battle in which all of the Valkyries, Gods, and Valhallian souls would fight. However, only the Valkyries would lose their spiritual lives permanently, and have their souls destroyed. But, for the protection of soulkind. With great trust, Odin bestowed in them this knowledge",
                "Naturally, Snø, though we did not know her as Snø at this time, became afraid, but then, eager. She was ready to die for soulkind, but a change of plans was in her head. All warriors, regardless of anything must be permitted into Valhalla, and the entry into Fólkvangr should be as strict as possible, with only the best souls being admitted entry. The strategy was grow the spiritual realm's army and limit its liabilities." +
                        "\n" +
                        "Brann, however, was terrified at the thought of Ragnarok, and wholly disagreed with Sno. For one, limited entry to Fólkvangr would tear kin apart as they could not see one another. Second, only noble and wise warriors ascended to the ranks of Valhalla." +
                        "\n" +
                        "The fighting was endless. But Tulip, as we know her now, the third valkyrie, knew better; was it a test from the gods? Or was it serious? Either way, the stars' glint can be rewritten.",
                "https://media.discordapp.net/attachments/564923688621834251/1177158993098059887/image.png?ex=65717dbe&is=655f08be&hm=953893a56ce2a92f66634f7b72f7aeca05ee6ca1e7ad4fb900887cffad086b2a&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177167321937215498/image.png?ex=65718580&is=655f1080&hm=a83252e7d3ece9d3528b1686f53535a8abb572c21cb7ef345887bf3691edc941&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177173844734259220/image.png?ex=65718b93&is=655f1693&hm=ff82509c22c2beb34d5ab6416bdf08e5ba58b4ce5eab485c7a81348617810ac5&=&format=webp&quality=lossless");
        addStory(5,
                "Chapter Three",
                "Freyja gave the two quarreling Valkyries her wisdom. One, it is the god's final decision to know whom is worthy to enter Valhalla or Fólkvangr. Second, the fate of those in Ragnarok can be changed. The stars' glint *can* be rewritten, but what was seen, was seen. \"The quarrels must cease here\" uttered Freyja, and then she disappeared.",
                "However, the two Valkyries could not truly take Freyja's wisdom to heart. One fateful day, a batch of souls entered the in-between, the realm within the spirit realm of deciding factor for a soul's fate. It caused a great dispute between Brann and Snø, their anger and wraith within their spirits were reeling. The negative energy caused the souls to be cast into Helheim unnecessarily.",
                "Tulip, the third Valkyrie, came between them and told them \"Look! Look what you've done! The last souls have been cast to Helheim because of your fighting!\" Terrified, the two did not know what the Gods would think of this. A Valkyrie was never supposed to make a decision on a soul without the god's consultation, and Helheim was a place of eternal hellfire and destruction.",
                "https://media.discordapp.net/attachments/564923688621834251/1177421419710259210/image.png?ex=65727225&is=655ffd25&hm=cc9f47711dcd082068f6ee759134b01c5b95caa5bb15b2ff95a983535abd1c63&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177490707053821992/image.png?ex=6572b2ad&is=65603dad&hm=5853dee668453ef068c24f4fa538580e36d58df5392f107e0786ea53d6c20406&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177507910180405349/image.png?ex=6572c2b2&is=65604db2&hm=6078d75c6803dcea5eae36337c4e8993a8b490f6e5d452342248be8fd7c93cde&=&format=webp&quality=lossless");
        addStory(8,
                "Chapter Four",
                "The gods gathered the three Valkyries in their presence. \"What has transpired here in the spirit realm is a mistake never made in the history of our universe.\" began Odin. Freyja stepped forward, \"You *must* be banished to the physical realm. Perhaps there,\" she paused for a moment, \"... you will learn the true significance of mortals. The duty of a Valkyrie is of the highest importance, and must be executed nobly, and unitedly.\" added Freyja. However, Tulip as we know her now, the third Valkyrie, was free to remain in the spirit realm.",
                "The two Valkyrie’s peered into the realm. “Go on now” said Freyja. The two Valkyries pleaded to remain in the spirit realm. They could not imagine an existence of needing to eat or sleep, or a world without the wisdom of the gods at the tip of their paws.\n",
                "\"Freyja, if my Valkyrian siblings must be banished, banish me too. You know I alone have studied the physical realm and our subjects closely. They will not survive in their physical bodies long without a guide.” Propose the third Valkyrie, Tulip, as we know her now.\n Freyja knew she would ask this question, before the words even left her mouth. It was expected. Was she just to throw the other Valkyries in a world where they would surely die without knowledge of mortal life? No, the third Valkyrie (Tulip) was a perfect fit. Freyja simply hoped it would be enough.",
                "https://media.discordapp.net/attachments/564923688621834251/1177511780734484490/image.png?ex=6572c64d&is=6560514d&hm=a888152706f3bd4381129ad305d0d26ce10522d2653a635fd54e5b973950d576&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177526087450890280/image.png?ex=6572d3a0&is=65605ea0&hm=6fb75a51160d13acbf841fb77c34f3d61ddaddfafb02db98bdb606b959c7e033&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177520848731648040/image.png?ex=6572cebf&is=656059bf&hm=a0168d77e676a17f806df681db033ca98f2447f7e8150ef57fbc2ed2321733c6&=&format=webp&quality=lossless");
        addStory(12,
                "Chapter Five",
                "\"It is so then. All three of you must be banished to the spirit realm.\" agreed Freyja. The need for the Valkyries was to lift some load off the gods, but mostly, they wanted to have fellow family figures to join them to rest happily and rule the heavens with. The gods more than anyone understand the importance of family and kin. Perhaps, though, allowing even half-mortals to reside with the gods proves disastrous. She continued, \"Whilst in your physical forms, you will learn that your bodies do not age, and you are stronger than the average mortal. The Valkyrian god in you is apart of your soul so strong, you cannot escape it even in the physical realm. However, the demi side of you is still present; you must still eat, sleep, and drink, as any other mortal\"",
                "Freyja looked up, and whispered to the third Valkyrie, Tulip, \"My third Valkyrie, here is what I foresee in the stars: Your two Valkyrian siblings shall find a secluded place to settle. You will teach them how to survive, and, just as we do, they will go on to rule subjects. However, they will not get along, and that is where you must protect them from themselves for as long as possible. They will not make up until Ragnarok comes, and by then it will be too late. Remember, the stars may align, but their glint can be rewritten\"",
                "The three Valkyries were cast into the physical realm headfirst. Only, they knew nothing of what to expect as they had never stepped out of the spirit realm, for only the third Valkyrie, Tulip, had done so before.",
                "https://media.discordapp.net/attachments/564923688621834251/1177520848731648040/image.png?ex=6572cebf&is=656059bf&hm=a0168d77e676a17f806df681db033ca98f2447f7e8150ef57fbc2ed2321733c6&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177522693248135230/image.png?ex=6572d077&is=65605b77&hm=ce74fcd8093eb166d43eecd895ca6006c6bf9366cd49891859f4d54fcfe3b954&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1177526087450890280/image.png?ex=6572d3a0&is=65605ea0&hm=6fb75a51160d13acbf841fb77c34f3d61ddaddfafb02db98bdb606b959c7e033&=&format=webp&quality=lossless");
        addStory(15,
                "Chapter Six",
                "\"So it is settled. We must endure our banishment to the physical realm. Remember, you need to eat, sleep, drink water, everything a mortal must do. The one thing about us is that we appear to have better physical strength than our fellow animal counterparts, and, we do not age.\" said Tulip" +
                        "\n" +
                        " \"Enjoy your time as a physical being, you can feel it right now, can't you? The chill in the air? The breeze in your fur? A feeling you've never been able to experience before?\" continued Tulip.\n" +
                        "\n" +
                        "\"You may also noticed our physical coats have been matched to that of our surroundings. In the spirit realm, we did not have color, for our coats were only an illusion of what was to be there. But now they're-\" she stopped for a moment.",
                "\"..Well! Whoever said it would be perfect? We can't change our visual appearances, so do try your best to blend in.\" Tulip muttered." +
                        "\n" +
                        "\"And finally, we must choose names for ourselves. I chose Tulip a long time ago. My fur here seems to have the color of that specific flower...\"" +
                        "\n" +
                        "Brann, the first Valkryie, thought for a moment.. \"Brann is fitting. Red is the color of embers, right?\"\n" +
                        "\n" +
                        "\"Almost\" replied Tulip" +
                        "\n" +
                        "Snø also thought for a moment. \"And I suppose I will choose Sno, because Blue is the color of the snow, right?\"\n" +
                        "\n" +
                        "\"..Well-\" stammered Tulip",
                "\"Good\" Snø interrupted, \"Now what? I refuse to be around 'Brann'\" she said with disdain." +
                        "\n" +
                        "\"Well- If you just let me-\" continued Tulip" +
                        "\n" +
                        "\"..And I refuse to be around 'Snø'-- this whole name thing is ridiculous. But regardless, we're doomed to an eternal life of living as a mortal, including pain, and everything else. 'Tulip' at least spare me the misery of being around the one who caused all of this\" interrupted Brann" +
                        "\n" +
                        "Tulip took a big breath, and sighed. A lesson unlearned is a lesson that eventually must be learned. Or something, right...?",
                "https://media.discordapp.net/attachments/564923688621834251/1178269722219921438/image.png?ex=65758830&is=65631330&hm=4fa186404cd7067481345f37cf5e9bdc02374473c9df27151c037165ab056fa5&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178277973439287347/image.png?ex=65758fe0&is=65631ae0&hm=2ddbbca180662b8bea11d4323ad5df56e9a8af7b77b865221730463eeaf6e23d&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178269832878231584/image.png?ex=6575884b&is=6563134b&hm=1f291e7088d521809617fbf70cd32d891899aab076349017a0a3c2fffe640b48&=&format=webp&quality=lossless");
        addStory(18,
                "Chapter Seven",
                "\"Freyja specifically placed us in a secluded place known as The Cascades. If beings from the spirit realm met with the humans of the physical realm, it could create a divide in the universe. I'm not entirely sure *how* it works *really* but, I do know that it simply can not happen according to the gods.\" began Tulip." +
                        "\n" +
                        "\"Simply put, these forests are filled with creatures of our fellow species. Snø, you are a wildcat, and Brann, you are a fox. Both creatures are equal for the most part-- so please, do not fight. Now, you aren't *really* these creatures, you merely have their form and physical makeup. Understand?\"",
                "Both Brann and Snø examined their bodies. Tulip was right, the chill in the air, they could feel every moment in their mouth... Their faint heartbeats, their fragile organs...\n" +
                        "\n" +
                        "\"Yes\" they both replied at the same time",
                "\"Wonderful\" replied Tulip\n" +
                        "\n" +
                        "\"You two cannot remain in the spirit realm because you cannot work with one another after the prophecy of Ragnarok. I don't know how the stars are written, but you two need to work together. You will go on to collect subjects of the forest, of your own species, of  course. Your clans must work together, and then the gods will see your capability at unity once more. That, is my plan, anyway...\" Tulip went on, yet, knowing in her heart Freyja saw otherwise. What could a little hope hurt?\n" +
                        "\n" +
                        "The two former Valkyries, Brann and Snø, looked at one another with disdain, they may look normal in their expression, but their hearts were filled with rage and contempt. That said plenty.",
                "https://media.discordapp.net/attachments/564923688621834251/1178270667775410328/image.png?ex=65758912&is=65631412&hm=b5a02984c973bbfd6581512ce2b5ec5c2c84eb287f7407f30dbaacdac8b1b18d&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178271117690028072/image.png?ex=6575897d&is=6563147d&hm=ad98dd236408b11233f48eda28a69d675ec4b66a85051b3a9fb98fa17dcdf2e7&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178271069682008064/image.png?ex=65758972&is=65631472&hm=85a48109fb60818e017622bc67565cbe4a00d717576657780106e3657ea47d54&=&format=webp&quality=lossless");
        addStory(22,
                "Chapter Eight",
                "\"I will not work with you, not even the day Ragnarok falls upon us\" hissed Snø\n" +
                        "\n" +
                        "\"I could say the same, as you don't seem to care about surviving it.\" screeched Brann",
                "\"Have it your way, and see how Ragnarok goes when none of the Valkryies are present. Create your clans and settle before the cold winter sets in. Snø, claim the wildcats of the mountains, and Brann, claim the foxes of the forest.\" replied Tulip\n" +
                        "\n" +
                        "She continued, \"They will listen to you. They will feel a burr in their fur and a chill in their heart when they see you. They will know they are yours. It is not common for the souls of animals to go to the spirit realm, but they will have salvation with us former Valkyries, so said Odin and Freyja. Teach them in your way.\"\n" +
                        "\n" +
                        "\"One last thing I forgot to mention, you must kill animals-- smaller than you, specifically. That is where you will gain your nutrition. Does it seem cruel? Yes, but Odin allows for it. It is the circle of life. Better than any one being, you should know it.\"",
                "Brann and Snø went opposite directions that day, they built their clans before the winter set in, just as Tulip had asked.\n" +
                        "\n" +
                        "Tulip rose and said, \"One final thing my Valkrian siblings, one final thing-- At this spot, the spot of our dawn on The Cascades, we will meet again, everytime the moon turns full again, we will meet here, with all of the members of our clans, and we must discuss. The preparation for Ragnarok must be consistent.\"\n" +
                        "\n" +
                        "She continued, \"And, you mustn't see each other in battle. Otherwise, I will see to it I meet you in it as well\"\n" +
                        "\n" +
                        "After that, she fell, turned the other way, and walked off. Yet, the future would hold other plans. If they cannot meet one another in battle, then their subjects certainly *will*\n" +
                        "\n" +
                        "The Cascades was a model place. Long multicolored trees, delicate streams, snowy mountains, and an abundance of prey. For being banished, they had it pretty good..",
                "https://media.discordapp.net/attachments/564923688621834251/1178271069682008064/image.png?ex=65758972&is=65631472&hm=85a48109fb60818e017622bc67565cbe4a00d717576657780106e3657ea47d54&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178270099745013831/image.png?ex=6575888a&is=6563138a&hm=4d6f3cd7c437a09d0708dcf4b8db2636992847b2ba016d025022e1ad06f41424&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178269976495390760/image.png?ex=6575886d&is=6563136d&hm=4f87a4dd64ac1ec464f998504e0e932056409bf1b60f38457bcd788421be567b&=&format=webp&quality=lossless");
        addStory(25,
                "Chapter Nine",
                "Snø became leader of what was known as The Snowcrest. Mountainous regions with abundant prey further down near the southern borders. Nestled inside were warm camp-dens in the middle of a blizzarding mountain. She ruled the wildcats that called the place home before her\n",
                "Brann, on the hand, claimed their own homeland, taking in the foxes of the inhabiting forests. Abundant prey filled the forest floor, and with clean water in the abundant lakes as well.\n",
                "For being banished, this seemed like more of a blessing. The only real issue was, well.. each other. Brann closing in on Snø's borders or vice versa, stray clan members from the other wandering into unowned territory, it was always something.\n" +
                        "\n" +
                        "However, Tulip's words stood true; their respective subjects clang to the two leaders naturally, it's as if they knew. At this rate, Freyja's words may also hold true: *\"This will only be resolved come Ragnarok, and by then, it will have been too late.\"*\n" +
                        "\n" +
                        "Tulip, however, nested herself away from the two clans, living in solidarity. She followed Freyja's words, to keep them from each other, and ensuring they live up to their promise they had declared on the day of the reclamation.",
                "https://media.discordapp.net/attachments/564923688621834251/1178272738301988974/image.png?ex=65758b00&is=65631600&hm=3a9168ca4b8889fea80a295b38364e4af7a1c9c33e132e9e8ee4c0c4d201d886&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178272663718875166/image.png?ex=65758aee&is=656315ee&hm=31aab5f0795efacbe406595022aa92bb9679507dc19616dce5c11e188a04618f&=&format=webp&quality=lossless",
                "https://media.discordapp.net/attachments/564923688621834251/1178272968019820634/image.png?ex=65758b36&is=65631636&hm=f69d054112f29a05ff5e39221d749207184f06a49fc1d8f5f27a2820e79a80da&=&format=webp&quality=lossless");

        return connection;
    }

    public void createStoryDatabaseIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS stories (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, required_level INT, name TEXT, part_1 TEXT, part_2 TEXT, part_3 TEXT, image_1 TEXT, image_2 TEXT, image_3 TEXT)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void addStory(int requiredLevel, String name, String part1, String part2, String part3, String image1, String image2, String image3) throws SQLException {

        if (doesStoryExist(name)) { return; }

        String sql = "INSERT INTO stories (required_level, name, part_1, part_2, part_3, image_1, image_2, image_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, requiredLevel);
            statement.setString(2, name);
            statement.setString(3, part1);
            statement.setString(4, part2);
            statement.setString(5, part3);
            statement.setString(6, image1);
            statement.setString(7, image2);
            statement.setString(8, image3);

            statement.executeUpdate();
        }
    }


    public Story getStoryByLevel(int userLevel) throws SQLException {

        String query = "SELECT * FROM Stories WHERE required_level >= ? ORDER BY required_level DESC LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userLevel);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int requiredLevel = resultSet.getInt("required_level");
                String name = resultSet.getString("name");
                String part1 = resultSet.getString("part_1");
                String part2 = resultSet.getString("part_2");
                String part3 = resultSet.getString("part_3");
                String image1 = resultSet.getString("image_1");
                String image2 = resultSet.getString("image_2");
                String image3 = resultSet.getString("image_3");

                return new Story(id, requiredLevel, name, part1, part2, part3, image1, image2, image3);
            }
        }
        return null;
    }

    private boolean doesStoryExist(String storyName) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM stories WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storyName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }



}
