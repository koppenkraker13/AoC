import java.util.ArrayList;

public class Packet {
    private int version;
    private int id;
    private int length;
    private ArrayList<Packet> content = new ArrayList<>();
    private long literalValue;
    private String unusedData = "";

    public Packet(String bitRepresentation) {
        if (!bitRepresentation.equals("")) {
            this.version = Integer.parseInt(bitRepresentation.substring(0, 3), 2);
            this.id = Integer.parseInt(bitRepresentation.substring(3, 6), 2);
            remainingData(bitRepresentation.substring(6));
        }
    }

    public void remainingData(String bitRepresentation) {
        if (this.id == 4) {
            getPieces(bitRepresentation);
        } else {
            if (bitRepresentation.charAt(0) == '0') {
                this.length = Integer.parseInt(bitRepresentation.substring(1, 16), 2);
                getSubPackets0(bitRepresentation.substring(16, 16 + this.length));
                this.unusedData = bitRepresentation.substring(16 + this.length);
            } else {
                this.length = Integer.parseInt(bitRepresentation.substring(1, 12), 2);
                getSubPackets1(bitRepresentation.substring(12));
            }
        }
    }

    public void getSubPackets1(String unusedData) {
        while (this.content.size() < this.length) {
            Packet packet = new Packet(unusedData);
            content.add(packet);
            unusedData = packet.unusedData;
        }
        this.unusedData = unusedData;
    }

    public void getSubPackets0(String unusedData) {
        while (!unusedData.equals("")) {
            Packet packet = new Packet(unusedData);
            content.add(packet);
            unusedData = packet.unusedData;
        }
    }

    public void getPieces(String bitRepresentation) {
        ArrayList<String> list = new ArrayList<>();
        String result = "";
        for (int i = 0; i < bitRepresentation.length() / 5; i++) {
            list.add(bitRepresentation.substring(i * 5, i * 5 + 5));
        }
        for (String piece : list) {
            if (Integer.parseInt(piece, 2) > 15) {
                result += piece.substring(1);
            } else {
                result += piece.substring(1);
                break;
            }
        }
        this.literalValue = Long.parseLong(result, 2);
        this.unusedData += bitRepresentation.substring((result.length() / 4) + result.length());
    }

    public long getValue() {
        switch (this.id) {
            case 0:
                long total0 = 0;
                for (Packet packet : this.content) {
                    total0 += packet.getValue();
                }
                return total0;
            case 1:
                long total1 = 1;
                for (Packet packet : this.content) {
                    total1 *= packet.getValue();
                }
                return total1;
            case 2:
                long total2 = Long.MAX_VALUE;
                for (Packet packet : this.content) {
                    total2 = Math.min(total2, packet.getValue());
                }
                return total2;
            case 3:
                long total3 = 0;
                for (Packet packet : this.content) {
                    total3 = Math.max(total3, packet.getValue());
                }
                return total3;
            case 4:
                return this.value();
            case 5:
                return this.content.get(0).getValue() > this.content.get(1).getValue() ? 1 : 0;
            case 6:
                return this.content.get(0).getValue() < this.content.get(1).getValue() ? 1 : 0;
            case 7:
                return this.content.get(0).getValue() == this.content.get(1).getValue() ? 1 : 0;
        }
        return 0;
    }

    public int getAllVersions() {
        if (this.id == 4) {
            return this.version;
        } else {
            int total = this.version;
            for (Packet packet : content) {
                total += packet.getAllVersions();
            }
            return total;
        }
    }

    public long value() {
        return this.literalValue;
    }
}
