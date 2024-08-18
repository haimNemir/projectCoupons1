package Utils;

import java.util.Random;

public enum RandomCompanyName {
     ADP, Aetna, AgilentTechnologies, AIG, Akamai, AlignTechnology, Altria, AMD, Aon, AppDynamics, Aptina, AristaNetworks, ArrowElectronics, Asurion, ATT, Avaya, Axon, BakerHughes, BallAerospace, BMCSoftware, Brocade, CA, CadenceDesignSystems, Cargill, CarMax, CBRE, Cegid, Centene, CharlesSchwab, Chubb, Cigna, Citrix, CommVault, Concur, ConocoPhillips, Corning, Coupa, Cray, CrowdStrike, Cvent, DB, Dentsu, DeluxeCorporation, Domo, DXC, EastmanChemical, Eaton, eBay, ElectronicArts, Embraer, EmergentBioSolutions, EnphaseEnergy, Epicor, Equinix, Ericsson, Esri, Etsy, Evernote, Experian, F5Networks, FIS, Fitbit, FTIConsulting, Garmin, GE, Genentech, Genworth, GitHub, GlobeNewswire, Globant, Grainger, Guidewire, HCLTech, HenrySchein, Highmark, Honeywell, HP, HubSpot, IHSMarkit, Illumina, Infor, InfuSystem, Infosys, Instructure, IntactFinancial, Intel, InterSystems, Intuit, JuniperNetworks, Kaseya, KeurigDrPepper, KeysightTechnologies, KPMG, LabCorp, LinkedIn, Lyft, Mavenir, Medtronic, Merck, MicroFocus, MicrochipTechnology, Microsoft, Motorola, Mylan, NetApp, NetSuite, NewRelic, Nielsen, Nokia, NortonLifeLock, NTTData, Okta, OneMainFinancial, OpenText, Oracle, PaloAltoNetworks, Paycor, Pegasystems, PayPal, Perspecta, PingIdentity, Pluralsight, PTC, Qualcomm, QuestDiagnostics, Rackspace, RedHat, RingCentral, Rivian, Rubrik, Salesforce, Samsung, SAP, SAS, ServiceNow, Sharp, Siemens, SiliconLabs, Snap, Snowflake, SolarWinds, Splunk, Squarespace, SSC, Swift, Tableau, TD, Teradata, TetraTech, TexasInstruments, Thales, TheHartford, ThermoFisher, ThoughtSpot, TMobile, Trimble, Twilio, Uber, Unisys, Veeva, Verizon, VMware, Volvo, WalkMe, Walmart, WEX, Workday, Xilinx, Xerox, Yext, Zendesk, ZebraTechnologies, Zoom, Zoetis, Zscaler;

    public static String getRandomName() {
        Random random = new Random();
        RandomCompanyName[] names = values();
        return names[random.nextInt(names.length)].toString();
    }
}

